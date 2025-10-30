package com.ddts.hikvision.dto;

public class PlaybackURLsRequest {
	private String beginTime;
	private String endTime;
	private String cameraIndexCode;
	private String recordType;
	private String protocol;
	private String transmode;
	private String uuid;
	private Integer requestWebsocketProtocol;
	private Integer mergeSegment;
	private Integer storageLocation;
	private Integer internationalStandardTime;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCameraIndexCode() {
		return cameraIndexCode;
	}

	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getTransmode() {
		return transmode;
	}

	public void setTransmode(String transmode) {
		this.transmode = transmode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getRequestWebsocketProtocol() {
		return requestWebsocketProtocol;
	}

	public void setRequestWebsocketProtocol(Integer requestWebsocketProtocol) {
		this.requestWebsocketProtocol = requestWebsocketProtocol;
	}

	public Integer getMergeSegment() {
		return mergeSegment;
	}

	public void setMergeSegment(Integer mergeSegment) {
		this.mergeSegment = mergeSegment;
	}

	public Integer getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(Integer storageLocation) {
		this.storageLocation = storageLocation;
	}

	public Integer getInternationalStandardTime() {
		return internationalStandardTime;
	}

	public void setInternationalStandardTime(Integer internationalStandardTime) {
		this.internationalStandardTime = internationalStandardTime;
	}
}
