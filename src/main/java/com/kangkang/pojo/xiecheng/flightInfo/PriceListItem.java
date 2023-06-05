package com.kangkang.pojo.xiecheng.flightInfo;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PriceListItem{

	@SerializedName("groupType")
	private String groupType;

	@SerializedName("baggage")
	private Baggage baggage;

	@SerializedName("defaultCollapse")
	private boolean defaultCollapse;

	@SerializedName("penalty")
	private Penalty penalty;

	@SerializedName("adultPrice")
	private int adultPrice;

	@SerializedName("curSegmentMiseryIndex")
	private int curSegmentMiseryIndex;

	@SerializedName("cabin")
	private String cabin;

	@SerializedName("priceTags")
	private List<PriceTagsItem> priceTags;

	@SerializedName("index")
	private int index;

	@SerializedName("policyFlag")
	private int policyFlag;

	@SerializedName("priceUnitList")
	private List<PriceUnitListItem> priceUnitList;

	@SerializedName("priority")
	private int priority;

	@SerializedName("miseryIndex")
	private int miseryIndex;

	@SerializedName("infantSoldOut")
	private boolean infantSoldOut;

	@SerializedName("preferentialTransit")
	private boolean preferentialTransit;

	@SerializedName("dAllowAsLowestPrice")
	private boolean dAllowAsLowestPrice;

	@SerializedName("invoiceType")
	private String invoiceType;

	@SerializedName("routeSearchToken")
	private String routeSearchToken;

	@SerializedName("restrictionList")
	private List<Object> restrictionList;

	@SerializedName("childPrice")
	private Object childPrice;

	@SerializedName("sortPrice")
	private int sortPrice;

	@SerializedName("freeOilFeeAndTax")
	private boolean freeOilFeeAndTax;

	@SerializedName("ticketCount")
	private int ticketCount;

	@SerializedName("infantPrice")
	private int infantPrice;

	@SerializedName("productType")
	private ProductType productType;

	public String getGroupType(){
		return groupType;
	}

	public Baggage getBaggage(){
		return baggage;
	}

	public boolean isDefaultCollapse(){
		return defaultCollapse;
	}

	public Penalty getPenalty(){
		return penalty;
	}

	public int getAdultPrice(){
		return adultPrice;
	}

	public int getCurSegmentMiseryIndex(){
		return curSegmentMiseryIndex;
	}

	public String getCabin(){
		return cabin;
	}

	public List<PriceTagsItem> getPriceTags(){
		return priceTags;
	}

	public int getIndex(){
		return index;
	}

	public int getPolicyFlag(){
		return policyFlag;
	}

	public List<PriceUnitListItem> getPriceUnitList(){
		return priceUnitList;
	}

	public int getPriority(){
		return priority;
	}

	public int getMiseryIndex(){
		return miseryIndex;
	}

	public boolean isInfantSoldOut(){
		return infantSoldOut;
	}

	public boolean isPreferentialTransit(){
		return preferentialTransit;
	}

	public boolean isDAllowAsLowestPrice(){
		return dAllowAsLowestPrice;
	}

	public String getInvoiceType(){
		return invoiceType;
	}

	public String getRouteSearchToken(){
		return routeSearchToken;
	}

	public List<Object> getRestrictionList(){
		return restrictionList;
	}

	public Object getChildPrice(){
		return childPrice;
	}

	public int getSortPrice(){
		return sortPrice;
	}

	public boolean isFreeOilFeeAndTax(){
		return freeOilFeeAndTax;
	}

	public int getTicketCount(){
		return ticketCount;
	}

	public int getInfantPrice(){
		return infantPrice;
	}

	public ProductType getProductType(){
		return productType;
	}
}