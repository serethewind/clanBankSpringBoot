package com.serethewind.clanbank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
// @RequiredArgsConstructor For specifying a custom constructor.
@AllArgsConstructor
@Entity //converts the class User to database table
@Table(name = "users")
//specifies the name of the database. Without setting, it the name of the class is automatically the name of the database.
public class User {
    /**
     * FirstName, lastName, otherName(optional), accountNumber, accountBalance, phoneNumber1, phoneNumber2, emailAddress, Gender, Address, Religion, Date of Birth, bvn, referralCode
     */
    @Id //makes the id instance the primary key. Primary key uniquely identifies each record.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //the strategy for setting the id for new user created. The system will do this.
    private Long id;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    private String otherName;

    @Size(min = 10, max = 10) //using size already makes the nullable false.
    private String accountNumber;
    private double accountBalance; //to be set to zero later
    @Size(min = 7, max = 15)
    private String phoneNumber;

    private String alternativePhoneNumber;
    @Email
    private String email;
    @Size(min = 4, max = 6)
    private String gender;
    @Size(min = 5, max = 5000)
    private String address;
    @Size(min = 3)
    private String religion;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(min = 11, max = 11)
    private String bvn;
    @Size(min = 10, max = 10)
    private String referralCode;
    @Size(min=6)
    private String password;
}
