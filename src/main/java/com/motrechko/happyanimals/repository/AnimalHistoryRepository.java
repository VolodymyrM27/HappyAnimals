package com.motrechko.happyanimals.repository;

import com.motrechko.happyanimals.entity.AnimalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalHistoryRepository extends JpaRepository<AnimalHistory, Long> {
    List<AnimalHistory> findByAnimal_Id(Long id);

}