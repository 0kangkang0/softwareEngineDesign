package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RecommendInfoListItem{

	@SerializedName("duration")
	private int duration;

	@SerializedName("miseryIndex")
	private int miseryIndex;

	@SerializedName("flightSegmentList")
	private List<FlightSegmentListItem> flightSegmentList;

	@SerializedName("salePrice")
	private int salePrice;

	@SerializedName("totalPrice")
	private int totalPrice;

	@SerializedName("adjacentDistance")
	private int adjacentDistance;

	@SerializedName("recommendType")
	private String recommendType;

	@SerializedName("tax")
	private int tax;

	@SerializedName("title")
	private String title;

	public int getDuration(){
		return duration;
	}

	public int getMiseryIndex(){
		return miseryIndex;
	}

	public List<FlightSegmentListItem> getFlightSegmentList(){
		return flightSegmentList;
	}

	public int getSalePrice(){
		return salePrice;
	}

	public int getTotalPrice(){
		return totalPrice;
	}

	public int getAdjacentDistance(){
		return adjacentDistance;
	}

	public String getRecommendType(){
		return recommendType;
	}

	public int getTax(){
		return tax;
	}

	public String getTitle(){
		return title;
	}
}