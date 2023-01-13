package com.example.module6demo.repository;

import com.example.module6demo.model.HouseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHouseStatusRepository extends JpaRepository<HouseStatus, Long> {
}
