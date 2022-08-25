package ecm.standalone.models;

public class Association {
	private String targetSpecificationId;
	private String targetSpecificationType;
	private String targetType;
	private String id;
	private String associationType;
	
	public String getTargetSpecificationId() {
		return targetSpecificationId;
	}
	public void setTargetSpecificationId(String targetSpecificationId) {
		this.targetSpecificationId = targetSpecificationId;
	}
	public String getTargetSpecificationType() {
		return targetSpecificationType;
	}
	public void setTargetSpecificationType(String targetSpecificationType) {
		this.targetSpecificationType = targetSpecificationType;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssociationType() {
		return associationType;
	}
	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}
}
