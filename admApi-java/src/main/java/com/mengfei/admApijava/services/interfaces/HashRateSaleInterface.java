package com.mengfei.admApijava.services.interfaces;

import com.mengfei.admApijava.model.HashRateSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashRateSaleInterface extends JpaRepository<HashRateSale,Long> {

    @Query(value = "select * from hash_rate_sale h where h.miner_uuid=?1 and h.coin_uuid=?2",nativeQuery = true)
    List<HashRateSale> findByMinerUuidAndCoinUuid(String minerUuid, String coinUuid);


}
