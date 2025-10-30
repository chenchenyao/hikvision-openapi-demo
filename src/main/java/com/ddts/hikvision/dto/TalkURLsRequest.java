package com.ddts.hikvision.dto;

public class TalkURLsRequest {
	private String cameraIndexCode;
	private Integer transmode;
	private String protocol;

	public String getCameraIndexCode() {
		return cameraIndexCode;
	}

	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}

	public Integer getTransmode() {
		return transmode;
	}

	public void setTransmode(Integer transmode) {
		this.transmode = transmode;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
