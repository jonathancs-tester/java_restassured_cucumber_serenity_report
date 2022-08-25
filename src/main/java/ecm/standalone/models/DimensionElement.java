package ecm.standalone.models;

import java.util.List;

public class DimensionElement {
	
	public String id;
	public String description;
	public String state;
	public ValidFor validFor;
	public List<ValueDimensionElement> values;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ValueDimensionElement> getValues() {
		return values;
	}
	public void setValues(List<ValueDimensionElement> values) {
		this.values = values;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public ValidFor getValidFor() {
		return validFor;
	}
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}
}