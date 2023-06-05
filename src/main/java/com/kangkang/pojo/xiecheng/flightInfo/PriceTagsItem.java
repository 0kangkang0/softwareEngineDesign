package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
@lombok.Data
public class PriceTagsItem{

	@SerializedName("data")
	private Data data;

	@SerializedName("dtype")
	private String dtype;

	@SerializedName("weight")
	private Object weight;

	@SerializedName("label")
	private String label;

	public Data getData(){
		return data;
	}

	public String getDtype(){
		return dtype;
	}

	public Object getWeight(){
		return weight;
	}

	public String getLabel(){
		return label;
	}
}