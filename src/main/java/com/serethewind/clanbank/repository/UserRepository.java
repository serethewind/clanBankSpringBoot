package com.serethewind.clanbank.repository;

import com.serethewind.clanbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//the UserRepository allows us perform database operations like save, find, delete etc.
//JpaRepository<User, Long> User here is the entity class that we perform the database operations on. Long is the type of the primary key in the entity.
public interface UserRepository extends JpaRepository<User, Long>{
    //custom method to check if the email exists.

}