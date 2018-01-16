package Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model người dùng để đổ dữ liệu từ database vào
// output:
/////////////
public class User implements Parcelable {


    int userid;
    String nameuser;
    String email;
    int phonenumber;
    String password;
    String username;
    String ho;
    Date ngaythamgia;
    String honnhan;
    Date ngaysinh;
    String gioitinh;
    public Date getNgaythamgia() {
        return ngaythamgia;
    }

    public void setNgaythamgia(Date ngaythamgia) {
        this.ngaythamgia = ngaythamgia;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
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

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }



    public User(int userid, String nameuser, String email, int phonenumber, String password, String username,
                String ho, Date ngaythamgia,String honnhan,Date ngaysinh,String gioitinh) {
        this.userid = userid;
        this.nameuser = nameuser;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.username = username;
        this.ho=ho;
        this.ngaythamgia=ngaythamgia;
        this.honnhan=honnhan;
        this.ngaysinh=ngaysinh;
        this.gioitinh=gioitinh;
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
    protected User(Parcel in) {
        userid = in.readInt();
        nameuser = in.readString();
        email = in.readString();
        phonenumber = in.readInt();
        password = in.readString();
        username = in.readString();
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

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userid);
        dest.writeString(nameuser);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeInt(phonenumber);
    }
}
