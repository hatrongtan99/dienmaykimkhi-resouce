package com.hatrongtan99.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseAuditEntity {
    @CreatedBy
    @Column(updatable = false)
    private String createBy;

    @LastModifiedBy
    private String updateBy;

    @CreatedDate
    @Column(updatable = false)
    private Date createAt;

    @LastModifiedDate
    private  Date updateAt;
}
