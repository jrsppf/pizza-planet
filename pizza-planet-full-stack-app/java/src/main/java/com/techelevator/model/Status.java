package com.techelevator.model;

public class Status {
    private Integer statusId;
    private String statusDesc;

    public Status() {
        this.statusId = -1;
        this.statusDesc = "Default Status, should always be changed to something else";
    }

    public Status(Integer statusId, String statusDesc) {
        this.statusId = statusId;
        this.statusDesc = statusDesc;
    }

    public Integer getStatusId() {return statusId;}

    public void setStatusId(Integer statusId) {this.statusId = statusId;}

    public String getStatusDesc() {return statusDesc;}

    public void setStatusDesc(String statusDesc) {this.statusDesc = statusDesc;}
}
