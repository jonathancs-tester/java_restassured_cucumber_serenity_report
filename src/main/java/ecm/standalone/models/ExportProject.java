package ecm.standalone.models;

public class ExportProject {

  private String exportType;
  private String contentType;
  private String mode;
  private ExportCandidates exportCandidates;
  
  public String getExportType() {
		return exportType;
  }
  
  public void setExportType(String exportType) {
		this.exportType = exportType; 
  }
  
  public String getMode() {
	return mode;
  }

  public void setMode(String mode) {
	this.mode = mode;
  }

  public String getContentType() {
		return contentType;
  }
  
  public void setContentType(String contentType) {
		this.contentType = contentType;
  }
  
  public ExportCandidates getExportCandidates() {
		return exportCandidates;
  }
  
  public void setExportCandidates(ExportCandidates exportCandidates) {
		this.exportCandidates = exportCandidates;
  }
}
