package com.mengfei.admApijava.services.interfaces;


import com.mengfei.admApijava.model.Miner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MinerInterface extends JpaRepository<Miner,Long> {

    Miner findByMinerUuid(String minerUuid);






}
