package com.example.attendanceapp.modal;

import java.io.Serializable;

public class Batch implements Serializable {
    private int id;
    private String batchTime;
    private String batchSubject;
    private String toTime;
    private String fromTime;
    private String startDate;
    private String endDate;
    private int fid;
    private String status;

    public Batch(int id, String batchTime, String batchSubject, String toTime, String fromTime, String startDate, String endDate, int fid, String status) {
        this.id = id;
        this.batchTime = batchTime;
        this.batchSubject = batchSubject;
        this.toTime = toTime;
        this.fromTime = fromTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fid = fid;
        this.status = status;
    }

    public Batch(String batchTime, String batchSubject, String toTime, String fromTime, String startDate, String endDate, int fid, String status) {
        this.batchTime = batchTime;
        this.batchSubject = batchSubject;
        this.toTime = toTime;
        this.fromTime = fromTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fid = fid;
        this.status = status;
    }
    public Batch(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    public String getBatchSubject() {
        return batchSubject;
    }

    public void setBatchSubject(String batchSubject) {
        this.batchSubject = batchSubject;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
