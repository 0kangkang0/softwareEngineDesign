package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Penalty{

	@SerializedName("penaltyCriteria")
	private String penaltyCriteria;

	@SerializedName("defaultPenaltyTag")
	private String defaultPenaltyTag;

	@SerializedName("defaultPenalty")
	private DefaultPenalty defaultPenalty;

	public String getPenaltyCriteria(){
		return penaltyCriteria;
	}

	public String getDefaultPenaltyTag(){
		return defaultPenaltyTag;
	}

	public DefaultPenalty getDefaultPenalty(){
		return defaultPenalty;
	}
}