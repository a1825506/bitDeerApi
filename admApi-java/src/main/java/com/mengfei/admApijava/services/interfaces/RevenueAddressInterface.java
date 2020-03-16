package com.mengfei.admApijava.services.interfaces;

import com.mengfei.admApijava.model.RevenueAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RevenueAddressInterface extends JpaRepository<RevenueAddress,Long> {


    @Query(value = "select * from revenue_address h where h.user_uuid=?1 and h.coin_symbol=?2",nativeQuery = true)
    List<RevenueAddress> findByUserUuidAndCoinSymbol(String user_uuid,String coin_symbol);

    @Query(value = "select * from revenue_address h where h.user_uuid=?1 and h.address=?2",nativeQuery = true)
    List<RevenueAddress>  findByUserUuidAndAddress(String user_uuid,String address);


}
