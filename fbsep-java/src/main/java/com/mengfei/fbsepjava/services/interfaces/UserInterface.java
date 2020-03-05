package com.mengfei.fbsepjava.services.interfaces;

import com.mengfei.fbsepjava.model.Coin;
import com.mengfei.fbsepjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterface extends JpaRepository<User,Long> {

}
