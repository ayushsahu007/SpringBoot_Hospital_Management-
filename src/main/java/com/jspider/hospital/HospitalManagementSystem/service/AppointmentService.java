package com.jspider.hospital.HospitalManagementSystem.service;

import com.jspider.hospital.HospitalManagementSystem.model.Appointment;
import com.jspider.hospital.HospitalManagementSystem.model.Doctor;
import com.jspider.hospital.HospitalManagementSystem.model.Patient;
import com.jspider.hospital.HospitalManagementSystem.repository.AppointmentRepository;
import com.jspider.hospital.HospitalManagementSystem.repository.DoctorRepository;
import com.jspider.hospital.HospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createAppointment(Appointment appointment,Long doctorId,Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if (appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have an id");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        patient.getAppointments().add(appointment);//to maintain consistency

      return   appointmentRepository.save(appointment);


    }


}

