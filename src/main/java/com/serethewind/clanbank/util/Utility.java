package com.serethewind.clanbank.util;

import java.time.Year;

public class Utility {
    public static String generateAccountNumber(){
        Year yearOfUserRegistration = Year.now();
        int from = 100000;
        int to = 999999;
        int randomSixDigits = (int) Math.floor(Math.random() * (to - from + 1) + from);
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(yearOfUserRegistration).append(randomSixDigits);
        return accountNumber.toString();
    }
}
