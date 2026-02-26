package com.jspider.hospital.HospitalManagementSystem;

import com.jspider.hospital.HospitalManagementSystem.model.Appointment;
import com.jspider.hospital.HospitalManagementSystem.model.Insurance;
import com.jspider.hospital.HospitalManagementSystem.model.Patient;
import com.jspider.hospital.HospitalManagementSystem.service.AppointmentService;
import com.jspider.hospital.HospitalManagementSystem.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance(){
      Insurance insurance =  Insurance.builder()
              .policyNumber("HDFC_1234")
              .provider("HDFC")
              .validUntil(LocalDate.of(2030,12,12))
              .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

        var newPatient = insuranceService.disassociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void testAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,14,0,0))
                .reason("HeadAche")
                .build();

        var newAppointment = appointmentService.createAppointment(appointment, 1L, 2L);

      System.out.println(newAppointment);

      var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(),3L);

        System.out.println(updatedAppointment);    }
}
