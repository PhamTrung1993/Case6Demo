package com.example.module6demo.repository;

import com.example.module6demo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository<Rating, Long> {
}
