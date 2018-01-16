package Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model nhà hàng để đổ dữ liệu từ database vào
// output:
/////////////
public class Restaurant implements Parcelable {
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
    List<Image> lstimg;
    List<Comment> lstcomment;



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

    public void setRegisterday(Date registerday) {
        this.registerday = registerday;
    }

    public void setLstimg(List<Image> lstimg) {
        this.lstimg = lstimg;
    }

    public void setLstcomment(List<Comment> lstcomment) {
        this.lstcomment = lstcomment;
    }

    public Date getRegisterday() {
        return registerday;
    }
    public double getGiathapnhat() {
        return giathapnhat;
    }

    public String getMota() {
        return mota;
    }

    public double getGiacaonhat() {
        return giacaonhat;
    }

    public Time getFromTimeMoCua() {
        return fromTimeMoCua;
    }

    public Time getToTimeMoCua() {
        return toTimeMoCua;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public double getLat_location() {
        return lat_location;
    }

    public double getLong_location() {
        return long_location;
    }

    public Street getStreet() {
        return street;
    }

    public List<Image> getLstimg() {
        return lstimg;
    }

    public List<Comment> getLstcomment() {
        return lstcomment;
    }


    public Restaurant(){}

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

    protected Restaurant(Parcel in) {
        restid = in.readInt();
        namerest = in.readString();
        address = in.readString();
        type = in.readParcelable(TypeRestaurant.class.getClassLoader());
        dist = in.readParcelable(Restaurant.class.getClassLoader());
        street = in.readParcelable(Street.class.getClassLoader());
        phonenumber = in.readInt();
        toTimeMoCua = (java.sql.Time)in.readSerializable();
        fromTimeMoCua = (java.sql.Time)in.readSerializable();
        giathapnhat = in.readDouble();
        giacaonhat = in.readDouble();
        mota = in.readString();
        registerday = (java.sql.Date)in.readSerializable();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

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




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(restid);
        dest.writeString(namerest);
        dest.writeString(address);
        dest.writeParcelable(dist,flags);
        dest.writeParcelable(type,flags);
        dest.writeParcelable(street,flags);
        dest.writeInt(phonenumber);
        dest.writeSerializable(toTimeMoCua);
        dest.writeSerializable(fromTimeMoCua);
        dest.writeString(mota);
        dest.writeDouble(giathapnhat);
        dest.writeDouble(giacaonhat);
        dest.writeSerializable(registerday);
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
        this.lstimg = buider.lstimg;
        this.lstcomment = buider.lstcomment;
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
        List<Image> lstimg;
        List<Comment> lstcomment;
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
        public Builder setListImage(List<Image> lstimg){
            this.lstimg = lstimg;
            return this;
        }
        public Builder setListComment(List<Comment> lstcomment){
            this.lstcomment = lstcomment;
            return this;
        }

    }
}
