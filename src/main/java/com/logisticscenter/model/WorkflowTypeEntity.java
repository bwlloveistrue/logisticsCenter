package com.logisticscenter.model;

/**
 * 工作流entity
 */
public class WorkflowTypeEntity {

	public WorkflowTypeEntity() {

	}

	//标识ID
	private int id;
	//id
	private int key;

	//发送类型
	private String workflowType;

	private String description;

	private String workflowDesc;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.key = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}



	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.setWorkflowDesc(description);
	}

	public String getWorkflowDesc() {
		return workflowDesc;
	}

	public void setWorkflowDesc(String workflowDesc) {
		this.workflowDesc = workflowDesc;
	}
}
