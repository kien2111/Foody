package com.kien.group.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model bình luận để đổ dữ liệu từ database vào
// output:
/////////////
@XmlRootElement
public class Comment{
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
    
    @XmlElement Restaurant rest;
    @XmlElement User user;
    @XmlElement String comment;



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Comment(Restaurant rest, User user, String comment) {
        this.rest = rest;
        this.user = user;
        this.comment = comment;
    }

}
