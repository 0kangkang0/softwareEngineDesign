package com.kangkang.pojo.xiecheng.routeid;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FlightSegmentsItem{

	@SerializedName("arrivalAirportCode")
	private String arrivalAirportCode;

	@SerializedName("arrivalCityCode")
	private String arrivalCityCode;

	@SerializedName("arrivalProvinceId")
	private int arrivalProvinceId;

	@SerializedName("departureCityTimeZone")
	private int departureCityTimeZone;

	@SerializedName("departureProvinceId")
	private int departureProvinceId;

	@SerializedName("timeZone")
	private int timeZone;

	@SerializedName("arrivalCountryId")
	private int arrivalCountryId;

	@SerializedName("departureCityName")
	private String departureCityName;

	@SerializedName("departureCityCode")
	private String departureCityCode;

	@SerializedName("arrivalCityTimeZone")
	private int arrivalCityTimeZone;

	@SerializedName("departureCountryId")
	private int departureCountryId;

	@SerializedName("departureCityId")
	private int departureCityId;

	@SerializedName("arrivalCityId")
	private int arrivalCityId;

	@SerializedName("arrivalCityName")
	private String arrivalCityName;

	@SerializedName("departureDate")
	private String departureDate;

	@SerializedName("departureCountryName")
	private String departureCountryName;

	@SerializedName("arrivalCountryName")
	private String arrivalCountryName;

	@SerializedName("departureAirportName")
	private String departureAirportName;

	@SerializedName("departureAirportCode")
	private String departureAirportCode;

	@SerializedName("arrivalAirportName")
	private String arrivalAirportName;
}