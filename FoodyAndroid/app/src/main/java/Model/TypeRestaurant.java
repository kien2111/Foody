package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model loại nhà hàng để đổ dữ liệu từ database vào
// output:
/////////////
public class TypeRestaurant implements Parcelable {
    String typeid;
    String nametype;
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

    @Override
    public int describeContents() {
        return 0;
    }
    protected TypeRestaurant(Parcel in) {
        typeid = in.readString();
        nametype = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(typeid);
        out.writeString(nametype);

    }
    public static final Parcelable.Creator<TypeRestaurant> CREATOR
            = new Parcelable.Creator<TypeRestaurant>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public TypeRestaurant createFromParcel(Parcel in) {
            return new TypeRestaurant(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public TypeRestaurant[] newArray(int size) {
            return new TypeRestaurant[size];
        }
    };
}
