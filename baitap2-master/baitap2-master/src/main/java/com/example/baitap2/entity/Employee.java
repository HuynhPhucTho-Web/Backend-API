package com.example.baitap2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Full name must not be empty")
    @Size(max = 150, message = "Full name must be at most 150 characters")
    @Column(length = 150, nullable = false)
    private String fullName;

    private String address;

    @Pattern(regexp = "^\\+?\\d{7,20}$", message = "Phone must be 7-20 digits, optional leading +")
    @Column(length = 20)
    private String phone;

    private boolean status;

    // Quan hệ nhiều-nhân-viên - một-phòng-ban
    @ManyToOne
    @JoinColumn(name = "department_id") // khóa ngoại
    private Department department;
}
