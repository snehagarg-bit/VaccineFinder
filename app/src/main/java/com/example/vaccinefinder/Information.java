package com.example.vaccinefinder;

public class Information {
    private String centerName;
    private String centerAddress;
    private String centerFromTiming;
    private String centerToTiming;
    private String feeType;
    private String ageLimit;
    private String vaccineName;
    private String availableCapacity;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getCenterFromTiming() {
        return centerFromTiming;
    }

    public void setCenterFromTiming(String centerFromTiming) {
        this.centerFromTiming = centerFromTiming;
    }

    public String getCenterToTiming() {
        return centerToTiming;
    }

    public void setCenterToTiming(String centerToTiming) {
        this.centerToTiming = centerToTiming;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(String availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Information(String centerName, String centerAddress, String centerFromTiming, String centerToTiming, String feeType, String ageLimit, String vaccineName, String availableCapacity) {
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.centerFromTiming = centerFromTiming;
        this.centerToTiming = centerToTiming;
        this.feeType = feeType;
        this.ageLimit = ageLimit;
        this.vaccineName = vaccineName;
        this.availableCapacity = availableCapacity;
    }



        }