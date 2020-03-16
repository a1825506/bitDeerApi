package com.mengfei.admApijava.services.interfaces;

import com.mengfei.admApijava.model.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftCertificateInterface extends JpaRepository<GiftCertificate,Long> {

    GiftCertificate findByGiftUuid(String gift_uuid);
}
