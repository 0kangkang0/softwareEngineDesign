package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class HandBaggage{

	@SerializedName("baggageSizeLimit")
	private String baggageSizeLimit;

	@SerializedName("baggageContent")
	private String baggageContent;

	@SerializedName("hasFreeBaggage")
	private boolean hasFreeBaggage;

	@SerializedName("hasSizeLimit")
	private boolean hasSizeLimit;

	public String getBaggageSizeLimit(){
		return baggageSizeLimit;
	}

	public String getBaggageContent(){
		return baggageContent;
	}

	public boolean isHasFreeBaggage(){
		return hasFreeBaggage;
	}

	public boolean isHasSizeLimit(){
		return hasSizeLimit;
	}
}