package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PriceUnitListItem{

	@SerializedName("flightSeatList")
	private List<FlightSeatListItem> flightSeatList;

	public List<FlightSeatListItem> getFlightSeatList(){
		return flightSeatList;
	}
}