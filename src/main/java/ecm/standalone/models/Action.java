package ecm.standalone.models;

public class Action {
	private String id;
	private String name;
	private String orderItemAction;
	private String subAction;
	private String implementationType;
	private String implementationValue;
	private String condition;
	private ValidFor validFor;
	
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
	public String getOrderItemAction() {
		return orderItemAction;
	}
	public void setOrderItemAction(String orderItemAction) {
		this.orderItemAction = orderItemAction;
	}
	public String getSubAction() {
		return subAction;
	}
	public void setSubAction(String subAction) {
		this.subAction = subAction;
	}
	public String getImplementationType() {
		return implementationType;
	}
	public void setImplementationType(String implementationType) {
		this.implementationType = implementationType;
	}
	public String getImplementationValue() {
		return implementationValue;
	}
	public void setImplementationValue(String implementationValue) {
		this.implementationValue = implementationValue;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public ValidFor getValidFor() {
		return validFor;
	}
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}
}
