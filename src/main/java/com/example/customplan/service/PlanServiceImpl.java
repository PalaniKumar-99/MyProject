package com.example.customplan.service;

import com.example.customplan.entity.Plan;
import com.example.customplan.entity.PlanCategory;
import com.example.customplan.repository.PlanCategoryRepo;
import com.example.customplan.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanCategoryRepo planCategoryRepo;

    @Override
    public Map<Integer, String> getPlanCategories() {
        List<PlanCategory> categories = planCategoryRepo.findAll();
        Map<Integer, String> categoryMap= new HashMap<Integer, String>();
        categories.forEach(category ->
        {
            categoryMap.put(category.getCategoryId(), category.getCategoryName());
        });
        return categoryMap;
    }

    @Override
    public boolean savePlan(Plan plan) {

        Plan planDetails = planRepository.save(plan);
        if(planDetails.getPlanId()!= null)
        {
            return true;
        }
        return false;

        //return planDetails.getPlanId() != null ? true:false;

        //return planDetails.getPlanId() != null
    }

    @Override
    public List<Plan> getAllPlans() {
        List<Plan> listOfPlans = planRepository.findAll();
        return listOfPlans;
    }

    @Override
    public Plan getPlanById(Integer id) {
        return planRepository.findById(id).get();
    }

    @Override
    public boolean updatePlan(Plan plan) {
        Plan updatedPlan = planRepository.save(plan);
        return updatedPlan.getPlanId() != null;
    }

    @Override
    public boolean deletePlanById(Integer id) {
        boolean status = false;
        try{
            planRepository.deleteById(id);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean planStatusChange(Integer id, String activeSw) {

        Optional<Plan> existingPlan = planRepository.findById(id);
        if(existingPlan.isPresent()) {
            Plan oldPlan = existingPlan.get();
            oldPlan.setPlanActiveStatus(activeSw);
            planRepository.save(oldPlan);
            return true;
        }
        return false;
    }
}
