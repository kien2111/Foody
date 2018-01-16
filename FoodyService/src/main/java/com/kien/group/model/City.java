package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model thành phố và tỉnh để đổ dữ liệu vào
// output:
/////////////
@XmlRootElement
public class City{

	@XmlElement String cityid;
	@XmlElement String namecity;

    public City(String cityid, String namecity) {
        this.cityid = cityid;
        this.namecity = namecity;
    }
    public City(){

    }

    public String getNamecity() {
        return namecity;
    }

    public void setNamecity(String namecity) {
        this.namecity = namecity;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

   
      
}
