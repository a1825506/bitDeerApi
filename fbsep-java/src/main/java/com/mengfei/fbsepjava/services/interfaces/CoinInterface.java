package com.mengfei.fbsepjava.services.interfaces;

import com.mengfei.fbsepjava.model.Coin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinInterface extends JpaRepository<Coin,Long> {

}
