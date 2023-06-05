package com.kangkang.pojo.xiecheng.routeid;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ExtensionAttributes{

	@SerializedName("isFlightIntlNewUser")
	private boolean isFlightIntlNewUser;

	@SerializedName("LoggingSampling")
	private boolean loggingSampling;
}