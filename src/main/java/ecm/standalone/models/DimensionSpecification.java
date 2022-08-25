package ecm.standalone.models;

import java.beans.Transient;
import java.util.List;

public class DimensionSpecification {

	public String id;
	public String name;
	public String state;
	public ValidFor validFor;
	public String specificationType;
	public String type;
	public List<CharacteristicDimension> characteristics;

	public List<CharacteristicDimension> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(List<CharacteristicDimension> characteristics) {
		this.characteristics = characteristics;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpecificationType() {
		return specificationType;
	}
	public void setSpecificationType(String specificationType) {
		this.specificationType = specificationType;
	}
	
	@Transient
	public String getName() {
		return name;
	}
	
	@Transient
	public void setName(String name) {
		this.name = name;
	}
	
	@Transient
	public String getState() {
		return state;
	}
	
	@Transient
	public void setState(String state) {
		this.state = state;
	}
	
	@Transient
	public ValidFor getValidFor() {
		return validFor;
	}
	
	@Transient
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}
	
	@Transient
	public String getType() {
		return type;
	}
	
	@Transient
	public void setType(String type) {
		this.type = type;
	}
}