package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model quận huyện thị xã để đổ dữ liệu từ database vào
// output:
/////////////
@XmlRootElement
public class District{
	@XmlElement String distid;
	@XmlElement String namedist;
	@XmlElement City city;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }




    public District(String distid,String namedist,City city) {
        this.namedist = namedist;
        this.distid = distid;
        this.city = city;
    }

   


    public District() {
		
	}

	public String getDistid() {
        return distid;
    }

    public void setDistid(String distid) {
        this.distid = distid;
    }

    public String getNamedist() {
        return namedist;
    }

    public void setNamedist(String namedist) {
        this.namedist = namedist;
    }

  


}
