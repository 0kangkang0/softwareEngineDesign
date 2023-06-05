package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
@lombok.Data
public class FlightInfo{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private int status;

	public String getMsg(){
		return msg;
	}

	public Data getData(){
		return data;
	}

	public int getStatus(){
		return status;
	}
}