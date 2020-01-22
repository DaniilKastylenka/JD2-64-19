package by.it.academy.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", unique = true)
    @Access(AccessType.PROPERTY)
    private Long employeeId;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    @Access(AccessType.PROPERTY)
    private EmployeeAddress employeeAddress;

}
