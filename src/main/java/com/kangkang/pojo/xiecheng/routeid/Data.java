package com.kangkang.pojo.xiecheng.routeid;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@lombok.Data
public class Data{

	@SerializedName("extGlobalSwitches")
	private ExtGlobalSwitches extGlobalSwitches;

	@SerializedName("adultCount")
	private int adultCount;

	@SerializedName("extensionAttributes")
	private ExtensionAttributes extensionAttributes;

	@SerializedName("cabin")
	private String cabin;

	@SerializedName("childCount")
	private int childCount;

	@SerializedName("segmentNo")
	private int segmentNo;

	@SerializedName("transactionID")
	private String transactionID;

	@SerializedName("scope")
	private String scope;

	@SerializedName("flightSegments")
	private List<FlightSegmentsItem> flightSegments;

	@SerializedName("directFlight")
	private boolean directFlight;

	@SerializedName("flightWay")
	private String flightWay;

	@SerializedName("noRecommend")
	private boolean noRecommend;

	@SerializedName("infantCount")
	private int infantCount;
}