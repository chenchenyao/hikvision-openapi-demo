package com.ddts.hikvision.api;   //修改包路径

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ddts.hikvision.dto.*;
import com.ddts.hikvision.util.Tools;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.Response;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.hikvision.artemis.sdk.constant.Constants;
import com.hikvision.artemis.sdk.constant.SystemHeader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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

	//Control the PTZ
	public static String controlling(ControllingRequest controllingRequest) throws Exception {
		String controllingDataApi = ARTEMIS_PATH +"/api/video/v1/ptzs/controlling";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",controllingDataApi);
			}
		};
		controllingRequest.setCameraIndexCode("2");
		controllingRequest.setCommand("GOTO_PRESET");
		controllingRequest.setPresetIndex(20);
		controllingRequest.setAction(1);
		controllingRequest.setSpeed(4);
		controllingRequest.setPatrolIndex(5);

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
		controlling(new ControllingRequest());
	}

}
