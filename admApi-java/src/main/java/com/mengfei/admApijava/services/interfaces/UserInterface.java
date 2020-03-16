package com.mengfei.admApijava.services.interfaces;

import com.mengfei.admApijava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterface extends JpaRepository<User,Long> {

    User findByPhoneNum(String phoneNumber);

    User findByEmail(String email);

    User findByUserUuid(String findByUserUuid);

}
