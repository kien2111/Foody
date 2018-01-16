package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Street {
	@XmlElement int idduong;
	@XmlElement String tenduong;
	@XmlElement District dist;
	
	public Street(){}
	
	
	public Street(int idduong, String tenduong) {
		this.idduong = idduong;
		this.tenduong = tenduong;
	}
	
	public Street(int idduong, String tenduong, District dist) {
		this.idduong = idduong;
		this.tenduong = tenduong;
		this.dist = dist;
	}
	
	
	public int getIdduong() {
		return idduong;
	}
	public void setIdduong(int idduong) {
		this.idduong = idduong;
	}
	public String getTenduong() {
		return tenduong;
	}
	public void setTenduong(String tenduong) {
		this.tenduong = tenduong;
	}
	public District getDist() {
		return dist;
	}
	public void setDist(District dist) {
		this.dist = dist;
	}
	
	
	
}
