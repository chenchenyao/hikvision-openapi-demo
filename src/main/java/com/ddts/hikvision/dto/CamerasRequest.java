package com.ddts.hikvision.dto;

public class CamerasRequest {
	private Integer pageNo;
	private Integer pageSize;
	private String siteIndexCode;
	private String deviceType;
	private Integer bRecordSetting;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSiteIndexCode() {
		return siteIndexCode;
	}

	public void setSiteIndexCode(String siteIndexCode) {
		this.siteIndexCode = siteIndexCode;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getBRecordSetting() {
		return bRecordSetting;
	}

	public void setBRecordSetting(Integer bRecordSetting) {
		this.bRecordSetting = bRecordSetting;
	}
}
