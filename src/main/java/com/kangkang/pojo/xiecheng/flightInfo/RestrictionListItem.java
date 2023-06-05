package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RestrictionListItem{

	@SerializedName("description")
	private String description;

	@SerializedName("label")
	private String label;

	@SerializedName("childBookAdultTicket")
	private boolean childBookAdultTicket;

	@SerializedName("type")
	private String type;

	@SerializedName("justOneMatched")
	private boolean justOneMatched;

	public String getDescription(){
		return description;
	}

	public String getLabel(){
		return label;
	}

	public boolean isChildBookAdultTicket(){
		return childBookAdultTicket;
	}

	public String getType(){
		return type;
	}

	public boolean isJustOneMatched(){
		return justOneMatched;
	}
}