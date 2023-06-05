package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RecommendProduct{

	@SerializedName("recommendInfoList")
	private List<RecommendInfoListItem> recommendInfoList;

	public List<RecommendInfoListItem> getRecommendInfoList(){
		return recommendInfoList;
	}
}