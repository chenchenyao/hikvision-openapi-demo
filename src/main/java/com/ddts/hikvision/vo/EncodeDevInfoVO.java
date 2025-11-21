package com.ddts.hikvision.vo;

import lombok.Data;

/**
 * @author admin
 * @Description
 * @create 2025-11-04 15:28
 */
@Data
public class EncodeDevInfoVO {
    private String encodeDevIndexCode;
    private String encodeDevName;
    private String encodeDevIp;
    private String encodeDevPort;
    private String encodeDevCode;
    private String treatyType;
    private String status;
    private String isSupportWakeUp;
    private String wakeUpStatus;
    private String pictureStorePosType;

}
