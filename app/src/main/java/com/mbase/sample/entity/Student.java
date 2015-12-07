package com.mbase.sample.entity;

import com.mbase.monch.database.db.annotation.NotNull;
import com.mbase.monch.database.db.annotation.PrimaryKey;
import com.mbase.monch.database.db.annotation.Table;
import com.mbase.monch.database.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by monch on 15/12/7.
 */
@Table("Sutdent")
public class Student implements Serializable {

    private static final long serialVersionUID = -1;

    /**
     * 学生ID，AssignType.AUTO_INCREMENT为自增长主键，AssignType.BY_MYSELF为自定义主键
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public long id;

    /**
     * 学生姓名
     */
    public String name;

    /**
     * 学生性别，不能为空
     */
    @NotNull
    public int gender;

    public Student() { }

    public Student(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
