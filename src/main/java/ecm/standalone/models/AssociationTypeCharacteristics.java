package ecm.standalone.models;

import java.util.List;

public class AssociationTypeCharacteristics {
	public String id;
	public String name;
	public int sequence;
	public String valueType;
	public ValueTypeSpecification valueTypeSpecification;
	public List<CharacteristicValue> characteristicValues;
	public List<Property> properties;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public ValueTypeSpecification getValueTypeSpecification() {
		return valueTypeSpecification;
	}
	public void setValueTypeSpecification(ValueTypeSpecification valueTypeSpecification) {
		this.valueTypeSpecification = valueTypeSpecification;
	}
	public List<CharacteristicValue> getCharacteristicValues() {
		return characteristicValues;
	}
	public void setCharacteristicValues(List<CharacteristicValue> characteristicValues) {
		this.characteristicValues = characteristicValues;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}
