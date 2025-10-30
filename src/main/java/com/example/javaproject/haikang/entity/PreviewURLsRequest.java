package com.example.javaproject.haikang.entity;

public class PreviewURLsRequest {
	private String cameraIndexCode;
	private Integer streamType;
	private String protocol;
	private Integer transmode;
	private Integer requestWebsocketProtocol;

	public String getCameraIndexCode() {
		return cameraIndexCode;
	}

	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}

	public Integer getStreamType() {
		return streamType;
	}

	public void setStreamType(Integer streamType) {
		this.streamType = streamType;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getTransmode() {
		return transmode;
	}

	public void setTransmode(Integer transmode) {
		this.transmode = transmode;
	}

	public Integer getRequestWebsocketProtocol() {
		return requestWebsocketProtocol;
	}

	public void setRequestWebsocketProtocol(Integer requestWebsocketProtocol) {
		this.requestWebsocketProtocol = requestWebsocketProtocol;
	}
}
