package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class HoverDataListItem{

	@SerializedName("item")
	private String item;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private String title;

	public String getItem(){
		return item;
	}

	public String getType(){
		return type;
	}

	public String getTitle(){
		return title;
	}
}