package com.example.customplan.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Plan_Category")
@Data
public class PlanCategory {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "status")
    private String activeSw;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;
    @Column(name = "updated_by", insertable = false)
    @UpdateTimestamp
    private  LocalDate updatedDate;
}
