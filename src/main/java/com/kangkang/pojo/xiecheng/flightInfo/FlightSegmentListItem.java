package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FlightSegmentListItem{

	@SerializedName("arrivalCityCode")
	private String arrivalCityCode;

	@SerializedName("week")
	private String week;

	@SerializedName("departureCityName")
	private String departureCityName;

	@SerializedName("departureCityCode")
	private String departureCityCode;

	@SerializedName("arrivalCityName")
	private String arrivalCityName;

	@SerializedName("trafficType")
	private String trafficType;

	@SerializedName("intervalDays")
	private int intervalDays;

	@SerializedName("departureDate")
	private String departureDate;

	@SerializedName("flightNo")
	private String flightNo;

	public String getArrivalCityCode(){
		return arrivalCityCode;
	}

	public String getWeek(){
		return week;
	}

	public String getDepartureCityName(){
		return departureCityName;
	}

	public String getDepartureCityCode(){
		return departureCityCode;
	}

	public String getArrivalCityName(){
		return arrivalCityName;
	}

	public String getTrafficType(){
		return trafficType;
	}

	public int getIntervalDays(){
		return intervalDays;
	}

	public String getDepartureDate(){
		return departureDate;
	}

	public String getFlightNo(){
		return flightNo;
	}
}