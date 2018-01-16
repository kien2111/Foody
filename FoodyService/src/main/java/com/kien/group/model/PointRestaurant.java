package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model Ä‘iá»ƒm cá»§a má»—i ngÆ°á»�i cháº¥m cho 1 quĂ¡n Äƒn Ä‘á»• vĂ o tá»« database
// output:
/////////////
@XmlRootElement
public class PointRestaurant {
	@XmlElement int userid;
	@XmlElement int restid;
	@XmlElement double point;

    public PointRestaurant(double point, int restid, int userid) {
        this.point = point;
        this.restid = restid;
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRestid() {
        return restid;
    }

    public void setRestid(int restid) {
        this.restid = restid;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }


}
