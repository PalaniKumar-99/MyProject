package com.example.customplan.controller;

import com.example.customplan.entity.Plan;
import com.example.customplan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/plancategories")
    public ResponseEntity<Map<Integer, String>> getPlanCategories() {
        Map<Integer, String> planCategories = planService.getPlanCategories();
        return new ResponseEntity<Map<Integer, String>>(planCategories, HttpStatus.OK);

    }

    @PostMapping("/saveplan")
    public ResponseEntity<String> savePlans(@RequestBody Plan plan) {
        boolean savedPlan = planService.savePlan(plan);
        if(savedPlan) {
            return  new ResponseEntity<>("Plan saved", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Plan saved failed", HttpStatus.NOT_ACCEPTABLE);
        }
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
        if(planUpdated) {
            return new ResponseEntity<>("Plan Updated successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Plan not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteplan/{planId}")
    public ResponseEntity<String> deletePlanById(@PathVariable Integer planId) {
        boolean deletedPlan = planService.deletePlanById(planId);
        if(deletedPlan) {
            return  new ResponseEntity<>("Plan deleted with id :"+planId, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>("No plan found exist with the planid: "+planId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/statuschange/{id}/{status}")
    public ResponseEntity<String> statusChange(@PathVariable Integer id, @PathVariable String status) {
        boolean statusChanged = planService.planStatusChange(id, status);
        if(statusChanged) {
            return  new ResponseEntity<>("Plan status changed with planid: "+id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Plan not found with planid: "+id, HttpStatus.NOT_FOUND);
        }
    }
}
