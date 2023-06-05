package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class StopListItem{

	@SerializedName("duration")
	private int duration;

	@SerializedName("airportName")
	private String airportName;

	@SerializedName("luggageDirectType")
	private int luggageDirectType;

	@SerializedName("cityName")
	private String cityName;

	@SerializedName("cityCode")
	private String cityCode;

	@SerializedName("buildingShortName")
	private String buildingShortName;

	@SerializedName("airportCode")
	private String airportCode;

	@SerializedName("provinceId")
	private int provinceId;

	public int getDuration(){
		return duration;
	}

	public String getAirportName(){
		return airportName;
	}

	public int getLuggageDirectType(){
		return luggageDirectType;
	}

	public String getCityName(){
		return cityName;
	}

	public String getCityCode(){
		return cityCode;
	}

	public String getBuildingShortName(){
		return buildingShortName;
	}

	public String getAirportCode(){
		return airportCode;
	}

	public int getProvinceId(){
		return provinceId;
	}
}