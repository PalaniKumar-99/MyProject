package com.example.customplan.controller;

import com.example.customplan.constants.AppConstants;
import com.example.customplan.entity.Plan;
import com.example.customplan.properties.AppProperties;
import com.example.customplan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlanController {

    private PlanService planService;
    private AppProperties appProperties;
    private Map<String, String> messages;

    public PlanController(PlanService planService, AppProperties appProperties) {
        super();
        this.planService = planService;
        this.messages = appProperties.getMessages();
    }
    @GetMapping("/plancategories")
    public ResponseEntity<Map<Integer, String>> getPlanCategories() {
        Map<Integer, String> planCategories = planService.getPlanCategories();
        return new ResponseEntity<Map<Integer, String>>(planCategories, HttpStatus.OK);

    }

    @PostMapping("/saveplan")
    public ResponseEntity<String> savePlans(@RequestBody Plan plan) {
        String responseMessage = AppConstants.EMPTY_STR;
        boolean savedPlan = planService.savePlan(plan);
        if(savedPlan) {
            responseMessage = messages.get(AppConstants.PLAN_SAVE_SUCC);
        } else {
            responseMessage = messages.get(AppConstants.PLAN_SAVE_FAIL);
        }
        return  new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> getAllPlans() {
        List<Plan> allPlans = planService.getAllPlans();
        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }
    @GetMapping("/plans/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Integer id) {
        Plan planById = planService.getPlanById(id);
        return new ResponseEntity<>(planById, HttpStatus.OK);
    }
    @PutMapping("/updateplan")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
        boolean planUpdated = planService.updatePlan(plan);
        String responseMsg = AppConstants.EMPTY_STR;
        if(planUpdated) {
            responseMsg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
        }
        else {
            responseMsg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
        }
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    @DeleteMapping("/deleteplan/{planId}")
    public ResponseEntity<String> deletePlanById(@PathVariable Integer planId) {
        boolean deletedPlan = planService.deletePlanById(planId);
        String responseMsg = AppConstants.EMPTY_STR;
        if(deletedPlan) {
            responseMsg = messages.get(AppConstants.PLAN_DELETE_SUCC);
        } else {
            responseMsg = messages.get(AppConstants.PLAN_DELETE_FAIL);
        }
        return  new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/statuschange/{id}/{status}")
    public ResponseEntity<String> statusChange(@PathVariable Integer id, @PathVariable String status) {
        boolean statusChanged = planService.planStatusChange(id, status);
        String responseMsg = AppConstants.EMPTY_STR;
        if(statusChanged) {
            responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCC);
        } else {
            responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
        }
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }
}
