package com.example.customplan.repository;

import com.example.customplan.entity.PlanCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {
}
