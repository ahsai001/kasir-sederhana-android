package com.ahsailabs.kasirsederhana.ui.menu.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MenuResponse{

	@SerializedName("data")
	private List<MenuItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(List<MenuItem> data){
		this.data = data;
	}

	public List<MenuItem> getData(){
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