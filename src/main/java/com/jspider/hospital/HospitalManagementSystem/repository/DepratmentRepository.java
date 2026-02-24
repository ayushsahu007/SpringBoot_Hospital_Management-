package com.jspider.hospital.HospitalManagementSystem.repository;

import com.jspider.hospital.HospitalManagementSystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepratmentRepository extends JpaRepository<Department, Long> {
}
