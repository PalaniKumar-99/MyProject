package com.example.customplan.entity;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Master_Plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Integer planId;
    @Column(name = "plan_name")
    private String planName;
    @Column(name = "plan_start_date")
    private LocalDate planStartDate;
    @Column(name = "plan_end_date")
    private LocalDate planEndDate;
    @Column(name = "plan_status")
    private String planActiveStatus;
    @Column(name = "plan_category_id")
    private Integer planCategoryId;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;
    @Column(name = "updated_date", insertable = false)
    @UpdateTimestamp
    private LocalDate updatedDate;
}
