package com.smile.clz.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@JsonInclude(
        value = JsonInclude.Include.NON_NULL
)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class Class {
    private int classId;
    private String className;
    private Grade grade;
    private String classPart;
    private String classState;
    private Timestamp createdTime;
    private int  createdUserId;
    private Timestamp updatedTime;
    private int  updatedUserId;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getClassPart() {
        return classPart;
    }

    public void setClassPart(String classPart) {
        this.classPart = classPart;
    }

    public String getClassState() {
        return classState;
    }

    public void setClassState(String classState) {
        this.classState = classState;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public int getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(int createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(int updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", grade=" + grade +
                ", classPart='" + classPart + '\'' +
                ", classState='" + classState + '\'' +
                ", createdTime=" + createdTime +
                ", createdUserId=" + createdUserId +
                ", updatedTime=" + updatedTime +
                ", updatedUserId=" + updatedUserId +
                '}';
    }
}
