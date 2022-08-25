package ecm.standalone.models;

import java.util.List;

public class Item {
	private String id;
	private String humanReadableId;
	private List<Version> versions;
	private ValidFor validFor;
	
	public String gethId() {
		return humanReadableId;
	}
	public void sethId(String humanReadableId) {
		this.humanReadableId = humanReadableId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Version> getVersions() {
		return versions;
	}
	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}
	public ValidFor getValidFor() {
		return validFor;
	}
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}
}


