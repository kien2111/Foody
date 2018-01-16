package com.kien.group.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model nhà hàng để đổ dữ liệu từ database vào
// output:
/////////////
@XmlRootElement
public class Restaurant {
	@XmlElement int restid;
	public void setRestid(int restid) {
		this.restid = restid;
	}

	public void setNamerest(String namerest) {
		this.namerest = namerest;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDist(District dist) {
		this.dist = dist;
	}

	public void setType(TypeRestaurant type) {
		this.type = type;
	}

	public void setRegisterday(java.sql.Date registerday) {
		this.registerday = registerday;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public void setLat_location(double lat_location) {
		this.lat_location = lat_location;
	}

	public void setLong_location(double long_location) {
		this.long_location = long_location;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setToTimeMoCua(Time toTimeMoCua) {
		this.toTimeMoCua = toTimeMoCua;
	}

	public void setFromTimeMoCua(Time fromTimeMoCua) {
		this.fromTimeMoCua = fromTimeMoCua;
	}

	public void setGiathapnhat(double giathapnhat) {
		this.giathapnhat = giathapnhat;
	}

	public void setGiacaonhat(double giacaonhat) {
		this.giacaonhat = giacaonhat;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	@XmlElement String namerest;
	@XmlElement String address;
	@XmlElement District dist;
	@XmlElement TypeRestaurant type;
	@XmlElement java.sql.Date registerday;
	@XmlElement Street street;
	@XmlElement double lat_location;
	@XmlElement double long_location;
	
	@XmlElement int phonenumber;
	@XmlElement Time toTimeMoCua;
	@XmlElement Time fromTimeMoCua;
	@XmlElement double giathapnhat;
	@XmlElement double giacaonhat;
	@XmlElement String mota;
	@XmlElement List<Image> lstimg;
	@XmlElement List<Comment> lstcomment;
	public List<Image> getLstimg() {
		return lstimg;
	}

	public void setLstimg(List<Image> lstimg) {
		this.lstimg = lstimg;
	}

	public List<Comment> getLstcomment() {
		return lstcomment;
	}

	public void setLstcomment(List<Comment> lstcomment) {
		this.lstcomment = lstcomment;
	}

	
	public int getPhonenumber() {
		return phonenumber;
	}

	public Time getToTimeMoCua() {
		return toTimeMoCua;
	}

	public Time getFromTimeMoCua() {
		return fromTimeMoCua;
	}

	public double getGiathapnhat() {
		return giathapnhat;
	}

	public double getGiacaonhat() {
		return giacaonhat;
	}

	public String getMota() {
		return mota;
	}

	public Street getStreet() {
		return street;
	}

	public double getLat_location() {
		return lat_location;
	}

	public double getLong_location() {
		return long_location;
	}

	private Restaurant(Builder buider){
        this.restid = buider.restid;
        this.namerest = buider.namerest;
        this.address = buider.address;
        this.dist = buider.dist;
        this.type = buider.type;
        this.street = buider.street;
        this.lat_location = buider.lat_location;
        this.long_location = buider.long_location;
        this.phonenumber= buider.phonenumber;
        this.toTimeMoCua = buider.toTimeMoCua;
        this.fromTimeMoCua = buider.fromTimeMoCua;
        this.giathapnhat = buider.giathapnhat;
        this.giacaonhat =buider.giacaonhat;
        this.mota = buider.mota;
        this.registerday = buider.registerday;
    }
	
	
    public Restaurant(int restid, String namerest, String address, District dist, TypeRestaurant type,
			Date registerday) {
		this.restid = restid;
		this.namerest = namerest;
		this.address = address;
		this.dist = dist;
		this.type = type;
		this.registerday = registerday;
	}
    

	public Restaurant(int restid,String namerest,String address,District dist,TypeRestaurant type,Street street){
        this.restid=restid;
        this.address=address;
        this.namerest = namerest;
        this.dist = dist;
        this.type=type;
        this.street = street;

    }

    public Restaurant(int restid,String namerest){
        this.restid=restid;
        this.namerest = namerest;

    }

    public java.sql.Date getRegisterday() {
		return registerday;
	}

    public int getRestid() {
        return restid;
    }

    public String getNamerest() {
        return namerest;
    }

    public String getAddress() {
        return address;
    }

    public District getDist() {
        return dist;
    }
    
    public TypeRestaurant getType() {
        return type;
    }

    public static class Builder{
        int restid;
        String namerest;
        String address;
        District dist;
        TypeRestaurant type;
        Street street;
        double lat_location;
        double long_location;
        int phonenumber;
        Time toTimeMoCua;
        Time fromTimeMoCua;
        double giathapnhat;
        double giacaonhat;
        String mota;
        java.sql.Date registerday;
        public Builder(){

        }
        public Restaurant build(){
            return new Restaurant(this);
        }
        public Builder setRestid(int id){
            this.restid = id;
            return this;
        }
        public Builder setNamerest(String namerest){
            this.namerest = namerest;
            return this;
        }
        public Builder setAddress(String address){
            this.address = address;
            return this;
        }
        public Builder setDist(District dist){
            this.dist = dist;
            return this;
        }
        public Builder setTyperestaurant(TypeRestaurant type){
            this.type = type;
            return this;
        }
        public Builder setStreet(Street street){
            this.street = street;
            return this;
        }
        public Builder setLatlocation(double lat){
            this.lat_location = lat;
            return this;
        }
        public Builder setLonglocation(double Long){
            this.long_location = Long;
            return this;
        }
        public Builder setPhonenumber(int number){
            this.phonenumber = number;
            return this;
        }
        public Builder setToTimeMoCua(Time number){
            this.toTimeMoCua = number;
            return this;
        }
        public Builder setFromTimeMoCua(Time number){
            this.fromTimeMoCua = number;
            return this;
        }
        public Builder setGiathapnhat(double price){
            this.giathapnhat = price;
            return this;
        }
        public Builder setGiacaonhat(double price){
            this.giacaonhat = price;
            return this;
        }
        public Builder setMota(String mota){
            this.mota = mota;
            return this;
        }
        public Builder setRegisterday(java.sql.Date registerday){
            this.registerday = registerday;
            return this;
        }

    }
    
}
