package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Baggage{

	@SerializedName("iconType")
	private String iconType;

	@SerializedName("dataList")
	private List<DataListItem> dataList;

	@SerializedName("baggageTag")
	private String baggageTag;

	public String getIconType(){
		return iconType;
	}

	public List<DataListItem> getDataList(){
		return dataList;
	}

	public String getBaggageTag(){
		return baggageTag;
	}
}