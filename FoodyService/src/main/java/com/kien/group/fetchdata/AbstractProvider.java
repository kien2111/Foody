package com.kien.group.fetchdata;


import com.kien.group.dbconnect.DatabaseManagement;

public abstract class AbstractProvider {
	DatabaseManagement databasemanagement;
	public AbstractProvider(){
		databasemanagement=DatabaseManagement.getInstance();
	}
	
}
