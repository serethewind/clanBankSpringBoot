package com.serethewind.clanbank.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//the dto controls what the requestBody the user can send. the information in this class mirrors selected information in the user entity class.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String otherName;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String gender;

    private String address;

    private String religion;

    private String bvn;

    private String referralCode;

    private String password;

    private String email;
    private Date dateOfBirth;
}
