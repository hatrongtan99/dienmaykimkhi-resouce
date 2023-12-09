package com.hatrongtan99.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Setter
@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditEntity  {

    @CreatedBy
    @Column(updatable = false)
    private Long createBy;

    @LastModifiedBy
    private Long lastModifiedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createAt;

    @UpdateTimestamp
    private Date updateAt;
}
