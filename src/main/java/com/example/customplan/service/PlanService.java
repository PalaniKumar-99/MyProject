package com.example.customplan.service;

import com.example.customplan.entity.Plan;

import java.util.List;
import java.util.Map;

public interface PlanService {
    public Map<Integer, String> getPlanCategories();
    public boolean savePlan(Plan plan);
    public List<Plan> getAllPlans();
    public Plan getPlanById(Integer id);
    public boolean updatePlan(Plan plan);
    public  boolean deletePlanById(Integer id);
    public boolean planStatusChange(Integer id, String activeSw);

}
