package by.it.academy.project.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private Double salary;

    @Column
    private Long age;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    @Access(AccessType.PROPERTY)
    private EmployeeAddress employeeAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID")
    private  Department department;

    public Employee(String name, String surname, Double salary, Long age, Department department) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.age = age;
        this.department = department;
    }
}
