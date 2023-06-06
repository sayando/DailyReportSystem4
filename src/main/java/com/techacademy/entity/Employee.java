package com.techacademy.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    /** 名前。20桁。null不許可 */
    private String name;

    /** 削除フラグ */
    @Column(nullable = false)
    private Integer deleteFlag;

    /** 登録日時 */
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    /** 更新日時 */
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
  
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;
}
