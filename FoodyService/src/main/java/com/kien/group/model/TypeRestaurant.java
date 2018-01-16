package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model loại nhà hàng để đổ dữ liệu từ database vào
// output:
/////////////
@XmlRootElement
public class TypeRestaurant{
	@XmlElement String typeid;
	@XmlElement String nametype;
    public TypeRestaurant(String typeid, String nametype) {
        this.typeid = typeid;
        this.nametype = nametype;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getNametype() {
        return nametype;
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    
}
