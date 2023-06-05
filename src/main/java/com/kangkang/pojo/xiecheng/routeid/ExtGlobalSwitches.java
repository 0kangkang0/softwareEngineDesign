package com.kangkang.pojo.xiecheng.routeid;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ExtGlobalSwitches{

	@SerializedName("useAllRecommendSwitch")
	private boolean useAllRecommendSwitch;

	@SerializedName("unfoldPriceListSwitch")
	private boolean unfoldPriceListSwitch;
}