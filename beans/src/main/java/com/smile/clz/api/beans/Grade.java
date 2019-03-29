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
public class Grade {
    private int gradeId;
    private String gradeName;
    protected Zone zone;
    private String gradeState;
    private Timestamp createdTime;
    private int  createdUserId;
    private Timestamp updatedTime;
    private int  updatedUserId;

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public String getGradeState() {
        return gradeState;
    }

    public void setGradeState(String gradeState) {
        this.gradeState = gradeState;
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
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                ", zone=" + zone +
                ", gradeState='" + gradeState + '\'' +
                ", createdTime=" + createdTime +
                ", createdUserId=" + createdUserId +
                ", updatedTime=" + updatedTime +
                ", updatedUserId=" + updatedUserId +
                '}';
    }
}
