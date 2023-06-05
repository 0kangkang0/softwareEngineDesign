package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Context{

	@SerializedName("flag")
	private int flag;

	@SerializedName("searchId")
	private String searchId;

	@SerializedName("finished")
	private boolean finished;

	public int getFlag(){
		return flag;
	}

	public String getSearchId(){
		return searchId;
	}

	public boolean isFinished(){
		return finished;
	}
}