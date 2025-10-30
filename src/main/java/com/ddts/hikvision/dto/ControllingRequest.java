package com.ddts.hikvision.dto;

public class ControllingRequest {
	private String cameraIndexCode;
	private String command;
	private Integer presetIndex;
	private Integer action;
	private Integer speed;
	private Integer PatrolIndex;

	public String getCameraIndexCode() {
		return cameraIndexCode;
	}

	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Integer getPresetIndex() {
		return presetIndex;
	}

	public void setPresetIndex(Integer presetIndex) {
		this.presetIndex = presetIndex;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getPatrolIndex() {
		return PatrolIndex;
	}

	public void setPatrolIndex(Integer PatrolIndex) {
		this.PatrolIndex = PatrolIndex;
	}
}
