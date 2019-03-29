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
public class Zone {

    private int zoneId;
    private String zoneName;
    private String zoneState;
    private Timestamp createdTime;
    private int  createdUserId;
    private Timestamp updatedTime;
    private int  updatedUserId;

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneState() {
        return zoneState;
    }

    public void setZoneState(String zoneState) {
        this.zoneState = zoneState;
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
        return "Zone{" +
                "zoneId=" + zoneId +
                ", zoneName='" + zoneName + '\'' +
                ", zoneState='" + zoneState + '\'' +
                ", createdTime=" + createdTime +
                ", createdUserId=" + createdUserId +
                ", updatedTime=" + updatedTime +
                ", updatedUserId=" + updatedUserId +
                '}';
    }


}
