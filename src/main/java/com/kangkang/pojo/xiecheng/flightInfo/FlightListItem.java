package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FlightListItem{

	@SerializedName("marketAirlineCode")
	private String marketAirlineCode;

	@SerializedName("arrivalProvinceId")
	private int arrivalProvinceId;

	@SerializedName("arrivalCityCode")
	private String arrivalCityCode;

	@SerializedName("departureProvinceId")
	private int departureProvinceId;

	@SerializedName("departureAirportShortName")
	private String departureAirportShortName;

	@SerializedName("mealType")
	private String mealType;

	@SerializedName("duration")
	private int duration;

	@SerializedName("sequenceNo")
	private int sequenceNo;

	@SerializedName("arrivalAirportShortName")
	private String arrivalAirportShortName;

	@SerializedName("aircraftSize")
	private String aircraftSize;

	@SerializedName("departureCityCode")
	private String departureCityCode;

	@SerializedName("departureCityId")
	private int departureCityId;

	@SerializedName("departureDateTime")
	private String departureDateTime;

	@SerializedName("arrivalDateTime")
	private String arrivalDateTime;

	@SerializedName("departureCountryName")
	private String departureCountryName;

	@SerializedName("arrivalCountryName")
	private String arrivalCountryName;

	@SerializedName("departureAirportCode")
	private String departureAirportCode;

	@SerializedName("aircraftName")
	private String aircraftName;

	@SerializedName("departureTerminal")
	private String departureTerminal;

	@SerializedName("arrivalAirportCode")
	private String arrivalAirportCode;

	@SerializedName("highLightPlaneNo")
	private boolean highLightPlaneNo;

	@SerializedName("trafficType")
	private String trafficType;

	@SerializedName("istTransitService")
	private boolean istTransitService;

	@SerializedName("stopCount")
	private int stopCount;

	@SerializedName("stopList")
	private List<Object> stopList;

	@SerializedName("flightNo")
	private String flightNo;

	@SerializedName("departureCityName")
	private String departureCityName;

	@SerializedName("arrivalCityId")
	private int arrivalCityId;

	@SerializedName("arrivalPunctuality")
	private String arrivalPunctuality;

	@SerializedName("arrivalCityName")
	private String arrivalCityName;

	@SerializedName("aircraftCode")
	private String aircraftCode;

	@SerializedName("leakedVisaTagSwitch")
	private boolean leakedVisaTagSwitch;

	@SerializedName("departureAirportName")
	private String departureAirportName;

	@SerializedName("transferDuration")
	private int transferDuration;

	@SerializedName("arrivalTerminal")
	private String arrivalTerminal;

	@SerializedName("marketAirlineName")
	private String marketAirlineName;

	@SerializedName("arrivalAirportName")
	private String arrivalAirportName;

	@SerializedName("operateAirlineName")
	private String operateAirlineName;

	@SerializedName("operateFlightNo")
	private String operateFlightNo;

	@SerializedName("operateAirlineCode")
	private String operateAirlineCode;

	public String getMarketAirlineCode(){
		return marketAirlineCode;
	}

	public int getArrivalProvinceId(){
		return arrivalProvinceId;
	}

	public String getArrivalCityCode(){
		return arrivalCityCode;
	}

	public int getDepartureProvinceId(){
		return departureProvinceId;
	}

	public String getDepartureAirportShortName(){
		return departureAirportShortName;
	}

	public String getMealType(){
		return mealType;
	}

	public int getDuration(){
		return duration;
	}

	public int getSequenceNo(){
		return sequenceNo;
	}

	public String getArrivalAirportShortName(){
		return arrivalAirportShortName;
	}

	public String getAircraftSize(){
		return aircraftSize;
	}

	public String getDepartureCityCode(){
		return departureCityCode;
	}

	public int getDepartureCityId(){
		return departureCityId;
	}

	public String getDepartureDateTime(){
		return departureDateTime;
	}

	public String getArrivalDateTime(){
		return arrivalDateTime;
	}

	public String getDepartureCountryName(){
		return departureCountryName;
	}

	public String getArrivalCountryName(){
		return arrivalCountryName;
	}

	public String getDepartureAirportCode(){
		return departureAirportCode;
	}

	public String getAircraftName(){
		return aircraftName;
	}

	public String getDepartureTerminal(){
		return departureTerminal;
	}

	public String getArrivalAirportCode(){
		return arrivalAirportCode;
	}

	public boolean isHighLightPlaneNo(){
		return highLightPlaneNo;
	}

	public String getTrafficType(){
		return trafficType;
	}

	public boolean isIstTransitService(){
		return istTransitService;
	}

	public int getStopCount(){
		return stopCount;
	}

	public List<Object> getStopList(){
		return stopList;
	}

	public String getFlightNo(){
		return flightNo;
	}

	public String getDepartureCityName(){
		return departureCityName;
	}

	public int getArrivalCityId(){
		return arrivalCityId;
	}

	public String getArrivalPunctuality(){
		return arrivalPunctuality;
	}

	public String getArrivalCityName(){
		return arrivalCityName;
	}

	public String getAircraftCode(){
		return aircraftCode;
	}

	public boolean isLeakedVisaTagSwitch(){
		return leakedVisaTagSwitch;
	}

	public String getDepartureAirportName(){
		return departureAirportName;
	}

	public int getTransferDuration(){
		return transferDuration;
	}

	public String getArrivalTerminal(){
		return arrivalTerminal;
	}

	public String getMarketAirlineName(){
		return marketAirlineName;
	}

	public String getArrivalAirportName(){
		return arrivalAirportName;
	}

	public String getOperateAirlineName(){
		return operateAirlineName;
	}

	public String getOperateFlightNo(){
		return operateFlightNo;
	}

	public String getOperateAirlineCode(){
		return operateAirlineCode;
	}
}