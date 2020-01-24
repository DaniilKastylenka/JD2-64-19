package by.it.academy.project.entity;

import lombok.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "EMPLOYEE_ADDRESS")
public class EmployeeAddress {

    @Id
    @GenericGenerator(name = "one-one", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "employee"))
    @GeneratedValue(generator = "one-one")
    @Column(name = "EMPLOYEE_ID") @Access(AccessType.PROPERTY)
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String postcode;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Access(AccessType.PROPERTY)
    @ToString.Exclude
    private Employee employee;

}
