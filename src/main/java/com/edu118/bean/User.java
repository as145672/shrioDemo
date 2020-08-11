package com.edu118.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;


@Data
@Accessors(chain = true)
@Table
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer uid;
    @Column(name = "username")
    private String username;
    @Column(name = "userPassword")
    private String password;
    @Column(name = "`describe`")
    private String describe;
    @Column(name = "`status`")
    private Integer status;

}

