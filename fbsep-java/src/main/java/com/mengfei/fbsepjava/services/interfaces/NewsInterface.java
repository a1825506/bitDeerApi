package com.mengfei.fbsepjava.services.interfaces;

import com.mengfei.fbsepjava.model.News;
import com.mengfei.fbsepjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsInterface extends JpaRepository<News,Long> {

    List<News> findByNewsType(int new_type);

}
