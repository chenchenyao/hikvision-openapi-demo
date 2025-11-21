package com.ddts.hikvision.api;   //修改包路径

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import com.ddts.hikvision.dto.*;
import com.ddts.hikvision.vo.AreaInfoVO;
import com.ddts.hikvision.vo.CameraInfoVO;
import com.ddts.hikvision.vo.EncodeDevInfoVO;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.hikvision.artemis.sdk.constant.Constants;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtemisTest {
	/**
	 * 请根据技术支持提供的实际的平台IP/端口和API网关中的合作方信息更换static静态块中的三个参数.
	 * [1 host]
	 * 		host格式为IP：Port，如10.0.0.1:443
	 * 		当使用https协议调用接口时，IP是平台（nginx）IP，Port是https协议的端口；
	 *     当使用http协议调用接口时，IP是artemis服务的IP，Port是artemis服务的端口（默认9016）。
	 * [2 appKey和appSecret]
	 * 		请按照技术支持提供的合作方Key和合作方Secret修改
	 * 	    appKey：合作方Key
	 * 	    appSecret：合作方Secret
	 * 调用前看清接口传入的是什么，是传入json就用doPostStringArtemis方法，是表单提交就用doPostFromArtemis方法
	 *
	 */
	/**
	 * API网关的后端服务上下文为：/artemis
	 */
	private static final String ARTEMIS_PATH = "/artemis";

	private static final ArtemisConfig CONFIG = new ArtemisConfig("192.168.6.222", "29107259", "tJ6FMSrZo6avZR7iBhoh");
	/**
	 * 根据需求调整超时时间
	 */
	static {
		//连接超时时间
		Constants.DEFAULT_TIMEOUT = 10000;
		//读取超时时间
		Constants.SOCKET_TIMEOUT = 60000;
	}

	//Get cameras information in page
	//获取摄像头信息
	public static String cameras(CamerasRequest camerasRequest) throws Exception {
		String getCamsApi = ARTEMIS_PATH + "/api/resource/v1/cameras";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", getCamsApi);
			}
		};
		camerasRequest.setPageNo(1);
		camerasRequest.setPageSize(10);
		String body = JSON.toJSONString(camerasRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null, null, "application/json");
		// 转换为 JSONObject
		JSONObject jsonObject = JSONObject.parseObject(result);
		String code = (String) jsonObject.get("code");
		String msg = (String) jsonObject.get("msg");
		System.out.println(code);
		System.out.println(msg);
		// 逐层获取 data -> list
		JSONObject data = jsonObject.getJSONObject("data");
		Integer total = (Integer)data.get("total");
		List<CameraInfoVO> cameraList1 = data.getList("list", CameraInfoVO.class);
		JSONArray listJson = (JSONArray) JSONPath.of("$.data.list").eval(jsonObject);
		List<CameraInfoVO> cameraList = listJson.toJavaList(CameraInfoVO.class);
		return result;
	}

	//Search for areas
	public static String regions(RegionsRequest regionsRequest) throws Exception {
		String regionsDataApi = ARTEMIS_PATH +"/api/resource/v1/regions";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",regionsDataApi);
			}
		};
		regionsRequest.setPageNo(1);
		regionsRequest.setPageSize(10);
		String body=JSON.toJSONString(regionsRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject data = jsonObject.getJSONObject("data");
		List<AreaInfoVO> areaList = data.getList("list", AreaInfoVO.class);
//		AreaInfoVO root1 = new AreaInfoVO("9", "4", "", "字字节点");
//		AreaInfoVO root2 = new AreaInfoVO("16", "2", "", "字节点");
//		areaList.add(root1);
//		areaList.add(root2);
		List<AreaInfoVO> areaTree = buildTree( areaList);
		return result;
	}

	public static List<AreaInfoVO> buildTree(List<AreaInfoVO> flatList) {
		Map<String, AreaInfoVO> nodeMap = new HashMap<>();
		List<AreaInfoVO> rootNodes = new ArrayList<>();
		// 第一步：把所有节点放入 map，按 indexCode 查找
		for (AreaInfoVO node : flatList) {
			nodeMap.put(node.getIndexCode(), node);
		}
		// 第二步：遍历每个节点，将其添加到父节点的 child 中
		for (AreaInfoVO node : flatList) {
			if (node.getParentIndexCode().equals("-1")) {
				rootNodes.add(node); // 是根节点
			} else {
				AreaInfoVO parent = nodeMap.get(node.getParentIndexCode());
				if (parent != null) {
					parent.getChild().add(node);
				}
			}
		}
		return rootNodes;
	}


	//Get encoding device list
	public static String encodeDeviceList(EncodeDeviceListRequest encodeDeviceListRequest) throws Exception {
		String encodeDeviceListDataApi = ARTEMIS_PATH +"/api/resource/v1/encodeDevice/encodeDeviceList";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",encodeDeviceListDataApi);
			}
		};
		encodeDeviceListRequest.setPageNo(1);
		encodeDeviceListRequest.setPageSize(10);
		String body=JSON.toJSONString(encodeDeviceListRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
	}


	//Get an encoding device information
	public static String encodeDeviceInfo(EncodeDeviceInfoRequest encodeDeviceInfoRequest) throws Exception {
		String encodeDeviceInfoDataApi = ARTEMIS_PATH +"/api/resource/v1/encodeDevice/indexCode/encodeDeviceInfo";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",encodeDeviceInfoDataApi);
			}
		};
		encodeDeviceInfoRequest.setEncodeDevIndexCode("9");
		String body=JSON.toJSONString(encodeDeviceInfoRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");

		JSONObject jsonObject = JSONObject.parseObject(result);

		EncodeDevInfoVO encodeDevInfo = jsonObject.getObject("data", EncodeDevInfoVO.class);

		return result;
	}


	//Get camera list of an area by area No
	public static String getCamerasByAreas(CamerasRequest camerasRequest) throws Exception {
		String camerasDataApi = ARTEMIS_PATH +"/api/resource/v1/regions/regionIndexCode/cameras";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",camerasDataApi);
			}
		};

		camerasRequest.setPageNo(1);
		camerasRequest.setPageSize(10);
		camerasRequest.setRegionIndexCode("4");
		String body=JSON.toJSONString(camerasRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");

		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject data = jsonObject.getJSONObject("data");
		List<CameraInfoVO> cameraList = data.getList("list", CameraInfoVO.class);


		return result;
	}


	public static void main(String[] args) throws Exception {
		//regions(new RegionsRequest());
		//encodeDeviceInfo(new EncodeDeviceInfoRequest());
		getCamerasByAreas(new CamerasRequest());
	}

}
