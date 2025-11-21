package com.ddts.hikvision.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @Description
 * @create 2025-11-04 15:28
 */
@Data
public class AreaInfoVO {
    private String indexCode;
    private String parentIndexCode;
    private String siteIndexCode;
    private String name;
    private List<AreaInfoVO> child = new ArrayList<>();

    public AreaInfoVO(String indexCode, String parentIndexCode, String siteIndexCode, String name) {
        this.indexCode = indexCode;
        this.parentIndexCode = parentIndexCode;
        this.siteIndexCode = siteIndexCode;
        this.name = name;
    }


    public AreaInfoVO() {
    }

    public List<AreaInfoVO> getChild() {
        return child;
    }


}
