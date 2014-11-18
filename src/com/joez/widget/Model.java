package com.joez.widget;

public class Model {
	private String name;
	private String description;
	public Model() {
	}
	public Model(int week,String name,String description){
		this.name=week+name;
		this.description=description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
