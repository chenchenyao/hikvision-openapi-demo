package com.ddts.hikvision.dto;

public class RegionsRequest {
	private Integer pageNo;
	private Integer pageSize;
	private String siteIndexCode;

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
}
