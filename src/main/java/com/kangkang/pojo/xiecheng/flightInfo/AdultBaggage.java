package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AdultBaggage{

	@SerializedName("baggageDesc")
	private String baggageDesc;

	@SerializedName("checkedBaggage")
	private CheckedBaggage checkedBaggage;

	@SerializedName("handBaggage")
	private HandBaggage handBaggage;

	public String getBaggageDesc(){
		return baggageDesc;
	}

	public CheckedBaggage getCheckedBaggage(){
		return checkedBaggage;
	}

	public HandBaggage getHandBaggage(){
		return handBaggage;
	}
}