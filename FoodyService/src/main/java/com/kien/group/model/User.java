package com.kien.group.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model ngÆ°á»�i dĂ¹ng Ä‘á»ƒ Ä‘á»• dá»¯ liá»‡u tá»« database vĂ o
// output:
/////////////
@XmlRootElement
public class User{


	@XmlElement int userid;
	@XmlElement String nameuser;
	@XmlElement String email;
	@XmlElement int phonenumber;
	@XmlElement String password;
	@XmlElement String username;
    //update
	@XmlElement String ho;
	@XmlElement Date ngaythamgia;
	@XmlElement String honnhan;
	@XmlElement Date ngaysinh;
	@XmlElement String gioitinh;
    public String getGioitinh() {
		return gioitinh;
	}


	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}


	public String getHo() {
		return ho;
	}


	public void setHo(String ho) {
		this.ho = ho;
	}


	public Date getNgaythamgia() {
		return ngaythamgia;
	}


	public void setNgaythamgia(Date ngaythamgia) {
		this.ngaythamgia = ngaythamgia;
	}


	public String getHonnhan() {
		return honnhan;
	}


	public void setHonnhan(String honnhan) {
		this.honnhan = honnhan;
	}


	public Date getNgaysinh() {
		return ngaysinh;
	}


	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public User(int userid, String nameuser,
    		String ho,
    		String honnhan,
    		Date ngaysinh,
    		String gioitinh) {
        this.userid = userid;
        this.nameuser = nameuser;      
        this.ho = ho;
        this.honnhan = honnhan;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
    }

    public User(int userid, String nameuser, String email,
    		int phonenumber,
    		String password,
    		String username,
    		String ho,
    		Date ngaythamgia,
    		String honnhan,
    		Date ngaysinh,String gioitinh) {
        this.userid = userid;
        this.nameuser = nameuser;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.username = username;
        this.ho = ho;
        this.ngaythamgia = ngaythamgia;
        this.honnhan = honnhan;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(int userid, String nameuser, String email, int phonenumber) {
        this.userid = userid;
        this.nameuser = nameuser;
        this.email = email;
        this.phonenumber = phonenumber;
    }


    public User(int userid, String nameuser) {
        this.userid = userid;
        this.nameuser = nameuser;
    }
    


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    
    
}
