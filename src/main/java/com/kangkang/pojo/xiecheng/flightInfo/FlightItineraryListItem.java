package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FlightItineraryListItem{

	@SerializedName("itineraryId")
	private String itineraryId;

	@SerializedName("flightSegments")
	private List<FlightSegmentsItem> flightSegments;

	@SerializedName("priceList")
	private List<PriceListItem> priceList;

	public String getItineraryId(){
		return itineraryId;
	}

	public List<FlightSegmentsItem> getFlightSegments(){
		return flightSegments;
	}

	public List<PriceListItem> getPriceList(){
		return priceList;
	}
}