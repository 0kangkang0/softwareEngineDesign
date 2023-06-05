package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FlightSegmentsItem{

	@SerializedName("stopCount")
	private int stopCount;

	@SerializedName("duration")
	private int duration;

	@SerializedName("transferRemark")
	private TransferRemark transferRemark;

	@SerializedName("flightList")
	private List<FlightListItem> flightList;

	@SerializedName("crossDays")
	private int crossDays;

	@SerializedName("airlineCode")
	private String airlineCode;

	@SerializedName("leakedVisaTagSwitch")
	private boolean leakedVisaTagSwitch;

	@SerializedName("transferCount")
	private int transferCount;

	@SerializedName("segmentNo")
	private int segmentNo;

	@SerializedName("airlineName")
	private String airlineName;

	@SerializedName("segmentType")
	private String segmentType;

	public int getStopCount(){
		return stopCount;
	}

	public int getDuration(){
		return duration;
	}

	public TransferRemark getTransferRemark(){
		return transferRemark;
	}

	public List<FlightListItem> getFlightList(){
		return flightList;
	}

	public int getCrossDays(){
		return crossDays;
	}

	public String getAirlineCode(){
		return airlineCode;
	}

	public boolean isLeakedVisaTagSwitch(){
		return leakedVisaTagSwitch;
	}

	public int getTransferCount(){
		return transferCount;
	}

	public int getSegmentNo(){
		return segmentNo;
	}

	public String getAirlineName(){
		return airlineName;
	}

	public String getSegmentType(){
		return segmentType;
	}
}