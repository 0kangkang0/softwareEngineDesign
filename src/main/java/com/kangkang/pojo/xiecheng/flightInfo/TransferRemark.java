package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class TransferRemark{

	@SerializedName("servicePackages")
	private List<Object> servicePackages;

	@SerializedName("transitReminder")
	private String transitReminder;

	@SerializedName("descriptions")
	private List<String> descriptions;

	public List<Object> getServicePackages(){
		return servicePackages;
	}

	public String getTransitReminder(){
		return transitReminder;
	}

	public List<String> getDescriptions(){
		return descriptions;
	}
}