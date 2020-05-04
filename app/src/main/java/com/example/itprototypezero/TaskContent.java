package com.example.itprototypezero;

public class TaskContent {
    String taskId,organisationID, inventoryName,inventoryID, location, remarks, logTime;
     long addedCost;


    public TaskContent(String taskID,String organistationID,String inventoryName,String inventoryID,String location,String remarks,long addedCost) {
        this.taskId = taskID;
        this.organisationID = organistationID;
        this.inventoryName = inventoryName;
        this.inventoryID = inventoryID;
        this.location = location;
        this.remarks = remarks;
        this.addedCost = addedCost;
    }


    public void setTaskId(){
        this.taskId  = taskId;
    }

    public void setOrganisationID(){
        this.organisationID = organisationID;
    }

    public void setInventoryName(){
        this.inventoryName = this.inventoryName;
    }

    public void setInventoryID(){
        this.inventoryID  = inventoryID;
    }

    public void setLocation(){
        this.location = location;
    }

    public void setRemarks(){
        this.remarks = remarks;
    }

    public void setAddedCost() {
        this.addedCost = addedCost;
    }

    public void setLogTime() {this.logTime = logTime;}

    public String getTaskId(){
        return this.taskId;
    }
    public String getOrganisationID(){
        return this.organisationID;
    }

    public String getInventoryName(){
        return this.inventoryName;
    }

    public String getInventoryID(){
        return this.inventoryID;
    }

    public String getLocation(){
        return this.location;
    }

    public String getRemarks(){
        return this.remarks;
    }

    public long getAddedCost() {
        return this.addedCost;
    }

    public String getLogTime() { return this.logTime; }

}
