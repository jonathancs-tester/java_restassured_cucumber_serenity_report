package ecm.standalone.models;

import java.util.List;

public class PriceSpecification {
	private String id;
	private List<Version> versions;
	
	public String getId() {
		return id;
	}
	public void setId(String ID) {
		this.id= ID;
	}
	public List<Version> getVersions() {
		return versions;
	}
	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}
}

