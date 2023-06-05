package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProductType{

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("dCategory")
	private String dCategory;

	@SerializedName("policyFlag")
	private int policyFlag;

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getDCategory(){
		return dCategory;
	}

	public int getPolicyFlag(){
		return policyFlag;
	}
}