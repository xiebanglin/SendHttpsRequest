package com.request.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.request.utils.OKHttpClientBuilder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class SendRequestController {

	@GetMapping("/sendPostRequest")
	public void sendHttpsRequest() {
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		String url = "https://127.0.0.1:8088/hello?request=https请求";
		Map<String, String> map = new HashMap<String, String>();
		map.put("request", "https请求");
		// 先转成JSON对象
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		// JSON对象转换为JSON字符串
		String json = jsonObject.toJSONString();
//		Request request = new Request.Builder().url(url).headers(Headers.of(new HashMap<>()))
//				.post(RequestBody.create(JSON, json)).build();
		Request request = new Request.Builder().url(url).headers(Headers.of(new HashMap<>()))
				.get().build();
		OKHttpClientBuilder.buildOKHttpClient().build().newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("onFailure()");
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) {
				System.out.println("onResponse()");
			}
		});
	}
}
