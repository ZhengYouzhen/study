package com.zyz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author 娃娃鱼
 * @date 2018/3/20 19:59
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "姓名不能为空")
    @Size(min = 1, max = 10, message = "姓名必须大于1并且小于10个字")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄大于 0")
    @Max(value = 300, message = "年龄不大于 300")
    private Integer age;

    @NotEmpty(message = "出生时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private String birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
