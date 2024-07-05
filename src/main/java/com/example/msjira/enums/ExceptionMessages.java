package com.example.msjira.enums;

public enum ExceptionMessages {
    TASK_NOT_FOUND , TELESALE_NOT_FOUND , REPORTER_NOT_FOUND;

    public String message(){
        return this.toString();
    }
    public String createLog(String methodName , String id){
        return String.format("Action.ERROR.%s | id : %s " , methodName , id);
    }

}
