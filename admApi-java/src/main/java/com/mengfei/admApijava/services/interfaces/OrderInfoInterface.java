package com.mengfei.admApijava.services.interfaces;


import com.mengfei.admApijava.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderInfoInterface extends JpaRepository<OrderInfo,Long> {



    @Query(value = "select * from order_info o where o.user_uuid=?1 and o.order_status=?2",nativeQuery = true)
    List<OrderInfo> findByUserUuidAndOrderStatus(String userUuid, int orderStatus);

    List<OrderInfo>  findByUserUuid(String user_uuid);
}
