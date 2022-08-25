package ecm.standalone.models;

import java.util.List;

public class ProductOfferingVersionExpanded  extends VersionExpanded{
	private String id;
	private boolean sellIndicator;
	private List<BusinessDatesModel> businessDatesModel;
	
	public String getIdItemVersion() {
		return id;
	}
	public void setIdItemVersion(String id) {
		this.id = id;
	}
	public boolean getSellIndicator() {
		return sellIndicator;
	}
	public void setSellIndicator(Boolean sellIndicator) {
		this.sellIndicator = sellIndicator;
	}
	public List<BusinessDatesModel> getBusinessDatesModel() {
		return businessDatesModel;
	}
	public void setBusinessDatesModel(List<BusinessDatesModel> businessDatesModels) {
		this.businessDatesModel = businessDatesModels;
	}
}
