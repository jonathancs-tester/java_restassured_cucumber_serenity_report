package ecm.standalone.models;

import java.beans.Transient;
import java.util.List;

public class Rule {
	private String id;
	private String type;
	private String name;
	private String ruleLanguage;
	private String ruleType;
	private String sequence;
	private List<RuleVersion> versions;
	private RuleSpaceSpec ruleSpaceSpec;
	private RuleAssociation rule;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<RuleVersion> getVersions() {
		return versions;
	}
	public void setVersions(List<RuleVersion> versions) {
		this.versions = versions;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRuleLanguage() {
		return ruleLanguage;
	}
	public void setRuleLanguage(String ruleLanguage) {
		this.ruleLanguage = ruleLanguage;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public RuleAssociation getRule() {
		return rule;
	}
	public void setRule(RuleAssociation rule) {
		this.rule = rule;
	}
	@Transient
	public RuleSpaceSpec getRuleSpaceSpec() {
		return ruleSpaceSpec;
	}
	@Transient
	public void setRuleSpaceSpec(RuleSpaceSpec ruleSpaceSpec) {
		this.ruleSpaceSpec = ruleSpaceSpec;
	}
}
