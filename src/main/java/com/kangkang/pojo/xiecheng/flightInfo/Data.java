package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
@lombok.Data
public class Data{

	@SerializedName("bestChoiceFlightsForceTop")
	private boolean bestChoiceFlightsForceTop;

	@SerializedName("flightItineraryList")
	private List<FlightItineraryListItem> flightItineraryList;

	@SerializedName("context")
	private Context context;

	@SerializedName("recommendProduct")
	private RecommendProduct recommendProduct;

	@SerializedName("giftIds")
	private String giftIds;

	@SerializedName("giftId")
	private int giftId;

	@SerializedName("cost")
	private int cost;

	@SerializedName("showInFlight")
	private boolean showInFlight;

	@SerializedName("tag")
	private String tag;

	@SerializedName("hoverDataList")
	private List<HoverDataListItem> hoverDataList;

	@SerializedName("code")
	private String code;

	@SerializedName("desc")
	private String desc;

	public boolean isBestChoiceFlightsForceTop(){
		return bestChoiceFlightsForceTop;
	}

	public List<FlightItineraryListItem> getFlightItineraryList(){
		return flightItineraryList;
	}

	public Context getContext(){
		return context;
	}

	public RecommendProduct getRecommendProduct(){
		return recommendProduct;
	}

	public String getGiftIds(){
		return giftIds;
	}

	public int getGiftId(){
		return giftId;
	}

	public int getCost(){
		return cost;
	}

	public boolean isShowInFlight(){
		return showInFlight;
	}

	public String getTag(){
		return tag;
	}

	public List<HoverDataListItem> getHoverDataList(){
		return hoverDataList;
	}

	public String getCode(){
		return code;
	}

	public String getDesc(){
		return desc;
	}
}