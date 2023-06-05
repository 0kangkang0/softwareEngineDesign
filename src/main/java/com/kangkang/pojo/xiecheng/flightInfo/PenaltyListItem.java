package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PenaltyListItem{

	@SerializedName("isVirtualFlight")
	private boolean isVirtualFlight;

	@SerializedName("label")
	private String label;

	@SerializedName("type")
	private int type;

	@SerializedName("content")
	private String content;

	public boolean isIsVirtualFlight(){
		return isVirtualFlight;
	}

	public String getLabel(){
		return label;
	}

	public int getType(){
		return type;
	}

	public String getContent(){
		return content;
	}
}