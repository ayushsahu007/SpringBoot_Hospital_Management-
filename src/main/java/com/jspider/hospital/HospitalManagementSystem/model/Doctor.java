package com.jspider.hospital.HospitalManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 100)
    private String specialization;

  @Column(nullable = false,unique = true,length = 100)
    private String email;

  @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments = new HashSet<>();

  @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments = new ArrayList<>();

}
