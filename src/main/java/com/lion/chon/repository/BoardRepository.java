package com.lion.chon.repository;

import com.lion.chon.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    Page<BoardEntity> findAll(Pageable pageable); // 페이징 기능 추가
}
