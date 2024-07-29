package com.lion.chon.repository;

import com.lion.chon.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {

    List<ApplicationEntity> findByUserEntity_Id(String id);

    List<ApplicationEntity> findByBoardEntity_Id(int id);

}
