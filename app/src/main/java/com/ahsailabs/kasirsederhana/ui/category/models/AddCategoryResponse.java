package com.ahsailabs.kasirsederhana.ui.category.models;

import com.google.gson.annotations.SerializedName;

public class AddCategoryResponse{

	@SerializedName("data")
	private CategoryItem data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(CategoryItem data){
		this.data = data;
	}

	public CategoryItem getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}