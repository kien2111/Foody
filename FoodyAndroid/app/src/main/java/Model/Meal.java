package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model món ăn đổ từ database vào
// output:
/////////////
public class Meal implements Parcelable {
    int mealid;
    String namemeal;
    Restaurant rest;

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

    protected Meal(Parcel in) {
        mealid = in.readInt();
        namemeal = in.readString();
        rest = in.readParcelable(Restaurant.class.getClassLoader());
        image = in.readParcelable(Image.class.getClassLoader());
    }
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mealid);
        out.writeString(namemeal);
        out.writeParcelable(rest,flags);
        out.writeParcelable(image,flags);
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



    public static final Parcelable.Creator<Meal> CREATOR
            = new Parcelable.Creator<Meal>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

}
