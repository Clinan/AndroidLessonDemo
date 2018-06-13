package cn.edu.hdu.lessiontest11.db.entity;

import java.io.Serializable;

/**
 * Created by arter on 2018/6/9.
 */

public class Staff implements BaseEntity, Serializable {
    private int id;
    private String name;
    private String sex;
    private String department;
    private float salary;

    public void setAll(int id, String name, String sex, String department, float salary) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

}
