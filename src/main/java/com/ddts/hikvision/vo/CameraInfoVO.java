package com.ddts.hikvision.vo;

/**
 * @author admin
 * @Description
 * @create 2025-11-04 15:28
 */
public class CameraInfoVO {
    private String cameraIndexCode;
    private String cameraName;
    private String capabilitySet;
    private String devResourceType;
    private String recordLocation;
    private String regionIndexCode;
    private String siteIndexCode;
    private Integer status;
    private Integer isSupportWakeUp;
    private Integer wakeUpStatus;

    public CameraInfoVO() {
    }


    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public void setCapabilitySet(String capabilitySet) {
        this.capabilitySet = capabilitySet;
    }

    public void setDevResourceType(String devResourceType) {
        this.devResourceType = devResourceType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void setEncodeDevIndexCode(String encodeDevIndexCode) {
        this.encodeDevIndexCode = encodeDevIndexCode;
    }

    public void setRecordLocation(String recordLocation) {
        this.recordLocation = recordLocation;
    }

    public void setRegionIndexCode(String regionIndexCode) {
        this.regionIndexCode = regionIndexCode;
    }

    public void setSiteIndexCode(String siteIndexCode) {
        this.siteIndexCode = siteIndexCode;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIsSupportWakeUp(Integer isSupportWakeUp) {
        this.isSupportWakeUp = isSupportWakeUp;
    }

    public void setWakeUpStatus(Integer wakeUpStatus) {
        this.wakeUpStatus = wakeUpStatus;
    }

    private String encodeDevIndexCode;
    private String recordType;

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public String getCameraName() {
        return cameraName;
    }

    public String getCapabilitySet() {
        return capabilitySet;
    }

    public String getDevResourceType() {
        return devResourceType;
    }

    public String getEncodeDevIndexCode() {
        return encodeDevIndexCode;
    }

    public String getRecordType() {
        return recordType;
    }

    public String getRecordLocation() {
        return recordLocation;
    }

    public String getRegionIndexCode() {
        return regionIndexCode;
    }

    public String getSiteIndexCode() {
        return siteIndexCode;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getIsSupportWakeUp() {
        return isSupportWakeUp;
    }

    public Integer getWakeUpStatus() {
        return wakeUpStatus;
    }

}
