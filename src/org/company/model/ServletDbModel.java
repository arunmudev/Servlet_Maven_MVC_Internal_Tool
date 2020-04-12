package org.company.model;

public class ServletDbModel {

	private Integer issueId;
	private String issueTitle;
	private String assignee;
	private String priority;

	/**
	 * Constructor
	 * @param title
	 */
	public ServletDbModel(Integer issueId,String issueTitle,String assignee,String priority) {
		this.issueId = issueId;
		this.issueTitle = issueTitle;
		this.assignee = assignee;
		this.priority=priority;
	}

	public void setIssueId(Integer issueId){
		this.issueId = issueId;
	}
	
	public Integer getIssueId(){
		return issueId;
	}
	
	public void setIssueTitle(String issueTitle){
		this.issueTitle = issueTitle;
	}
	
	public String getIssueTitle(){
		return issueTitle;
	}
	
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	public String getAssignee() {
		return assignee;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getPriority() {
		return priority;
	}
}