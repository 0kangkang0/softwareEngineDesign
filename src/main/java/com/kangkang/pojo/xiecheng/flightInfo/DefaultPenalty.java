package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DefaultPenalty{

	@SerializedName("penaltyList")
	private List<PenaltyListItem> penaltyList;

	@SerializedName("needServiceFee")
	private boolean needServiceFee;

	@SerializedName("title")
	private String title;

	@SerializedName("isDefaultText")
	private boolean isDefaultText;

	public List<PenaltyListItem> getPenaltyList(){
		return penaltyList;
	}

	public boolean isNeedServiceFee(){
		return needServiceFee;
	}

	public String getTitle(){
		return title;
	}

	public boolean isIsDefaultText(){
		return isDefaultText;
	}
}