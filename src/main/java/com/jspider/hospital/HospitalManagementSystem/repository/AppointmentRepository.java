package com.jspider.hospital.HospitalManagementSystem.repository;

import com.jspider.hospital.HospitalManagementSystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
}
