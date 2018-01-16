package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model hình ảnh để đổ dữ liệu vào từ database
// output:
/////////////
public class Image implements Parcelable{
    int imageid;
    Restaurant rest;
    User user;
    Meal meal;


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

    public static Creator<Image> getCREATOR() {
        return CREATOR;
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

    protected Image(Parcel in) {
        imageid = in.readInt();
        filepath = in.readString();
        type = in.readParcelable(TypeRestaurant.class.getClassLoader());
        rest = in.readParcelable(Restaurant.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
        meal = in.readParcelable(Meal.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(imageid);
        out.writeString(filepath);
        out.writeParcelable(type,flags);
        out.writeParcelable(rest,flags);
        out.writeParcelable(user,flags);
        out.writeParcelable(meal,flags);
    }

    public static final Parcelable.Creator<Image> CREATOR
            = new Parcelable.Creator<Image>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };


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


    @Override
    public int describeContents() {
        return 0;
    }


}
