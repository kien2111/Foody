package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model món ăn đổ từ database vào
// output:
/////////////
@XmlRootElement
public class Meal {
	@XmlElement int mealid;
	@XmlElement String namemeal;
	@XmlElement Restaurant rest;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    Image image;
    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }


    public Meal( int mealid,String namemeal,Image image) {
        this.namemeal = namemeal;
        this.mealid = mealid;
        this.image = image;
    }
    public Meal( int mealid,String namemeal,Restaurant rest,Image image) {
        this.namemeal = namemeal;
        this.mealid = mealid;
        this.rest = rest;
        this.image = image;
    }

   

    public String getNamemeal() {
        return namemeal;
    }

    public void setNamemeal(String namemeal) {
        this.namemeal = namemeal;
    }

    public int getMealid() {
        return mealid;
    }

    public void setMealid(int mealid) {
        this.mealid = mealid;
    }




}
