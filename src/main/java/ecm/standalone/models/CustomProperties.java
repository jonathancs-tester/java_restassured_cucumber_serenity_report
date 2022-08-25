package ecm.standalone.models;

public class CustomProperties {
	private String id;
	private ValidFor validFor;
	private String state;
	private PropertySpecification propertySpecification;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ValidFor getValidFor() {
		return validFor;
	}
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public PropertySpecification getPropertySpecification() {
		return propertySpecification;
	}
	public void setPropertySpecification(PropertySpecification propertySpecification) {
		this.propertySpecification = propertySpecification;
	}
}