package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DataListItem{

	@SerializedName("sequenceNo")
	private int sequenceNo;

	@SerializedName("adultBaggage")
	private AdultBaggage adultBaggage;

	@SerializedName("segmentNo")
	private int segmentNo;

	public int getSequenceNo(){
		return sequenceNo;
	}

	public AdultBaggage getAdultBaggage(){
		return adultBaggage;
	}

	public int getSegmentNo(){
		return segmentNo;
	}
}