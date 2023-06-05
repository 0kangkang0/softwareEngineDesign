package com.kangkang.pojo.xiecheng.routeid;

import com.google.gson.annotations.SerializedName;

@lombok.Data
public class RoutedId{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private int status;
}