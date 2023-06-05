package com.kangkang.pojo.xiecheng.flightInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FlightSeatListItem{

	@SerializedName("discountRate")
	private Object discountRate;

	@SerializedName("sequenceNo")
	private int sequenceNo;

	@SerializedName("accumulateMileage")
	private int accumulateMileage;

	@SerializedName("seatClass")
	private String seatClass;

	@SerializedName("invoiceType")
	private String invoiceType;

	@SerializedName("pid")
	private String pid;

	@SerializedName("segmentNo")
	private int segmentNo;

	@SerializedName("specialClassName")
	private String specialClassName;

	@SerializedName("specialClassDescription")
	private String specialClassDescription;

	@SerializedName("accumulateMileageText")
	private String accumulateMileageText;

	public Object getDiscountRate(){
		return discountRate;
	}

	public int getSequenceNo(){
		return sequenceNo;
	}

	public int getAccumulateMileage(){
		return accumulateMileage;
	}

	public String getSeatClass(){
		return seatClass;
	}

	public String getInvoiceType(){
		return invoiceType;
	}

	public String getPid(){
		return pid;
	}

	public int getSegmentNo(){
		return segmentNo;
	}

	public String getSpecialClassName(){
		return specialClassName;
	}

	public String getSpecialClassDescription(){
		return specialClassDescription;
	}

	public String getAccumulateMileageText(){
		return accumulateMileageText;
	}
}