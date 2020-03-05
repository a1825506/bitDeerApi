package com.mengfei.fbsepjava.services.interfaces;


import com.mengfei.fbsepjava.model.Miner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MinerInterface extends JpaRepository<Miner,Long> {

    Miner findByMinerUuid(String minerUuid);


    List<Miner> findAllByCoinUuid(String coinUuid);



}
