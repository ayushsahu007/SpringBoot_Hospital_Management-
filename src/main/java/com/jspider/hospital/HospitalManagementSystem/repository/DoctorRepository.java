package com.jspider.hospital.HospitalManagementSystem.repository;

import com.jspider.hospital.HospitalManagementSystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
