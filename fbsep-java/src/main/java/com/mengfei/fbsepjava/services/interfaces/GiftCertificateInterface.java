package com.mengfei.fbsepjava.services.interfaces;


import com.mengfei.fbsepjava.model.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftCertificateInterface extends JpaRepository<GiftCertificate,Long> {

    GiftCertificate findByGiftUuid(String gift_uuid);
}
