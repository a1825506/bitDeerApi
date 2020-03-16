package com.mengfei.admApijava.services.interfaces;

import com.mengfei.admApijava.model.Coin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinInterface extends JpaRepository<Coin,Long> {

   List<Coin> findByCoinUuid(String coin_uuid);



}
