package com.serethewind.clanbank.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String accountNumber;
    private double accountBalance;
    private String phoneNumber;
//    private String alternativePhoneNumber;
//    private String gender;

//    private String address;

//    private String religion;

//    private String bvn;

//    private String referralCode;

//    private String password;

    private String email;
//    private Date dateOfBirth;
}
