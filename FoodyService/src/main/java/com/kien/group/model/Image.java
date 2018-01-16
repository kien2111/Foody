package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model hình ảnh để đổ dữ liệu vào từ database
// output:
/////////////
@XmlRootElement
public class Image{
	@XmlElement int imageid;
	@XmlElement Restaurant rest;
	@XmlElement User user;
	@XmlElement Meal meal;


    public Meal getMeal() {
        return meal;
    }
    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeRestaurant getType() {
        return type;
    }

    public void setType(TypeRestaurant type) {
        this.type = type;
    }

   

    String filepath;
    TypeRestaurant type;



    public Image(int imageid,User user,String filepath){
        this.imageid=imageid;
        this.filepath = filepath;
        this.user = user;
    }


    public Image(int imageid,Restaurant rest,String filepath){
        this.imageid = imageid;
        this.filepath = filepath;
        this.rest = rest;
    }

    public Image(int imageid,TypeRestaurant typerest,String filepath){
        this.imageid = imageid;
        this.filepath = filepath;
        this.type = typerest;
    }
    public Image(int imageid,Meal meal,String filepath){
        this.imageid = imageid;
        this.filepath = filepath;
        this.meal = meal;
    }

    


    public Image() {
		// TODO Auto-generated constructor stub
	}
	public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }


    

}
