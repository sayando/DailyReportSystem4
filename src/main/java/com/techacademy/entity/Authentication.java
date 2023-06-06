package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data //Lombokのアノテーションです。「getter/setter、toString、hashCode、equals」のメソッドを生成します。
@Entity
@Table(name = "authentication")
@Component
public class Authentication {

    /** 権限用の列挙型 */
    public static enum Role {
        一般, 管理者
    }
    /** 従業員テーブルのID */
    @Id //主キーを表すアノテーション
    @Column(length = 20, nullable = false, unique = true)
    private String code;

    /** パスワード */
    @Column(length = 255, nullable = false)
    private String password;
 
    /** 権限 */
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    /** 社員番号 */
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;
}