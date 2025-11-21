package com.ddts.hikvision.api;   //修改包路径

import com.alibaba.fastjson2.*;
import com.ddts.hikvision.dto.*;
import com.ddts.hikvision.vo.CameraInfoVO;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.hikvision.artemis.sdk.constant.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hikvision.artemis.sdk.util.HttpUtil.wrapClient;

public class ArtemisPostTest {
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

	//Get version of platform
	//获取平台版本信息
	public static String version(VersionRequest versionRequest) throws Exception {
		String versionDataApi = ARTEMIS_PATH +"/api/common/v1/version";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",versionDataApi);
			}
		};
		String body=JSON.toJSONString(versionRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
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
//		// 方式1：通过 JSONPath 提取并转换（推荐）
//		List<CameraInfoVO> cameraList2 = JSONPath.of("$.data.list")// 创建路径
//				.eval(JSON.parseObject(result)) // 解析 JSON 并执行路径
//				.toList(CameraInfoVO.class); // 转换为目标列表
//
//		// 方式2：如果需要更明确的类型声明，可使用 TypeReference
//		List<CameraInfoVO> cameraList3 = JSONPath.of("$.data.list")
//				.eval(JSON.parseObject(result))
//				.to(new TypeReference<List<CameraInfoVO>>() {});

		JSONArray listJson = (JSONArray) JSONPath.of("$.data.list").eval(jsonObject);
		List<CameraInfoVO> cameraList = listJson.toJavaList(CameraInfoVO.class);

		return result;
	}


	//Get the streaming URL for live view
	//获取直播预览地址
	public static String previewURLs(PreviewURLsRequest previewURLsRequest) throws Exception {
		String previewURLsDataApi = ARTEMIS_PATH +"/api/video/v1/cameras/previewURLs";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",previewURLsDataApi);
			}
		};
		previewURLsRequest.setCameraIndexCode("2");
		previewURLsRequest.setStreamType(0);
		previewURLsRequest.setProtocol("websocket");
		previewURLsRequest.setTransmode(1);
		previewURLsRequest.setRequestWebsocketProtocol(0);
		String body=JSON.toJSONString(previewURLsRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
	}


	//Get the streaming URL for playback
	//获取回放浏览地址
	public static String playbackURLs(PlaybackURLsRequest playbackURLsRequest) throws Exception {
		String playbackURLsDataApi = ARTEMIS_PATH +"/api/video/v1/cameras/playbackURLs";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",playbackURLsDataApi);
			}
		};
		playbackURLsRequest.setBeginTime("2025-10-29T00:00:00+08:00");
		playbackURLsRequest.setEndTime("2025-10-30T00:00:00+08:00");
		playbackURLsRequest.setCameraIndexCode("2");
		playbackURLsRequest.setRecordType("0");
		playbackURLsRequest.setProtocol("websocket");
		playbackURLsRequest.setTransmode("1");
		playbackURLsRequest.setUuid("1");
		playbackURLsRequest.setRequestWebsocketProtocol(0);
		playbackURLsRequest.setMergeSegment(0);
		playbackURLsRequest.setStorageLocation(0);
		playbackURLsRequest.setInternationalStandardTime(0);
		String body=JSON.toJSONString(playbackURLsRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
	}

	//Get streaming URL for two way audio
	//获取双向音频地址
	public static String talkURLs(TalkURLsRequest talkURLsRequest) throws Exception {
		String talkURLsDataApi = ARTEMIS_PATH +"/api/video/v1/cameras/talkURLs";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",talkURLsDataApi);
			}
		};
		talkURLsRequest.setCameraIndexCode("2");
		talkURLsRequest.setTransmode(1);
		talkURLsRequest.setProtocol("rtsp");
		String body=JSON.toJSONString(talkURLsRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
	}


	//Download recordings
	public static String download(DownloadRequest downloadRequest) throws Exception {
		String downloadDataApi = ARTEMIS_PATH +"/api/video/v1/download";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",downloadDataApi);
			}
		};
		downloadRequest.setUrl("rtsp://10.18.66.12:554/sms/HCPEurl/commonvideobiz_yL3BB0IJEfmZXqcbVjpen%2BT1uYbybvT38kqzRR6XaakK2GcRosSrw4A9o%2FFtIeHzaYm3O%2Fmn%2FWOZwbiNhwhk9eTw1oiI8YFyIY5tLp5CJu7RnoINpei1W22NqnwyDt%2FcnLNZEnXoejzLazu9A0oXWVwxjsV99%2FtZXqJx8K7601%2BywBbErC80PzfVyFWd5HKmEz8NVdNCVT2fqokL8868ffQi8uMYNLXYSE0FEsqunLs%3D");
		downloadRequest.setAuthentication("Fsd8eugj2+RYG6EKEgN8/EHy6o5XPdkxD8t7Dy+EH6moE4G0X9+60C7PucJ8TlZOdGIK3lH5W7JDFdrercIsiJb1bl9Y52PGA/K/QXIff+84XMQ=");
		downloadRequest.setBeginTime("2022-09-07T19:38:16+08:00");
		downloadRequest.setEndTime("2022-09-07T19:38:16+08:00");
		downloadRequest.setVideoType(1);

		String body=JSON.toJSONString(downloadRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
	}


	//Get download progress and download URL by the download ID
	public static String downloadURL(DownloadURLRequest downloadURLRequest) throws Exception {
		String downloadURLDataApi = ARTEMIS_PATH +"/api/video/v1/downloadURL";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",downloadURLDataApi);
			}
		};
		downloadURLRequest.setDownloadID("");
		String body=JSON.toJSONString(downloadURLRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
	}

	//Control the PTZ

	/**
	 *云台控制指令（不区分大小写）："LEFT"（左平移）、"RIGHT"（右平移）、"UP"（上俯仰）、"DOWN"（下俯仰）、
	 * "ZOOM_IN"（放大）、"ZOOM_OUT"（缩小）、
	 * "LEFT_UP"（左平移+上俯仰）、"LEFT_DOWN"（左平移+下俯仰）、"RIGHT_UP"（右平移+上俯仰）、"RIGHT_DOWN"（右平移+下俯仰）、
	 * "FOCUS_NEAR"（调焦+）、"FOCUS_FAR"（调焦-）、"IRIS_ENLARGE"（光圈+）、"IRIS_REDUCE"（光圈-）、"GOTO_PRESET"（调用预置位）、"RUN_PATROL"（启动巡逻）。该字段值的最大长度为16位
	 *
	 */
	public static String controlling(ControllingRequest controllingRequest) throws Exception {
		String controllingDataApi = ARTEMIS_PATH +"/api/video/v1/ptzs/controlling";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",controllingDataApi);
			}
		};
		controllingRequest.setCameraIndexCode("10");
		controllingRequest.setCommand("RIGHT");
		controllingRequest.setPresetIndex(39);
		controllingRequest.setAction(0);
		controllingRequest.setSpeed(40);
		controllingRequest.setPatrolIndex(null);

		String body=JSON.toJSONString(controllingRequest);
		String result = ArtemisHttpUtil.doPostStringArtemis(CONFIG, path, body, null,  null, "application/json");
		return result;
	}


	public static void main(String[] args) throws Exception {
//		String cameraInfo = cameras(new CamerasRequest());
//		System.out.println(cameraInfo);
//		String liveViewUrl = previewURLs(new PreviewURLsRequest());
//		System.out.println(liveViewUrl);
//		String playback = playbackURLs(new PlaybackURLsRequest());
//		System.out.println(playback);
//		String version = version(new VersionRequest());
//		System.out.println(version);
//		String talkUrl = talkURLs(new TalkURLsRequest());
//		System.out.println(talkUrl);
		//download(new DownloadRequest());
		//controlling(new ControllingRequest());
	}

}
