package com.mengfei.admApijava.services.interfaces;

import com.mengfei.admApijava.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsInterface extends JpaRepository<News,Long> {

    List<News> findByNewsType(int new_type);

}
