package com.mengfei.fbsepjava.services.interfaces;

import com.mengfei.fbsepjava.model.Coin;

import com.mengfei.fbsepjava.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoInterface extends JpaRepository<OrderInfo,Long> {
}
