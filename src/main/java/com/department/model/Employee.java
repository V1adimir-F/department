package com.department.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vladimir F. 13.09.2022 13:19
 */

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity {

    @Column(name = "surname")
    private String surname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "personal_number")
    private String personalNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rank_id", referencedColumnName = "id")
    private Rank rank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(surname, employee.surname) && Objects.equals(firstname, employee.firstname) && Objects.equals(middleName, employee.middleName) && Objects.equals(birthday, employee.birthday) && Objects.equals(personalNumber, employee.personalNumber) && Objects.equals(rank, employee.rank) && Objects.equals(post, employee.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, firstname, middleName, birthday, personalNumber, rank, post);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                ", personalNumber='" + personalNumber + '\'' +
                ", rank=" + rank +
                ", post=" + post +
                '}';
    }
}
