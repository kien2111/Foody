package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model quận huyện thị xã để đổ dữ liệu từ database vào
// output:
/////////////
public class District implements Parcelable {
    String distid;
    String namedist;
    City city;

    public District() {

    }


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

    public District(Parcel in){
        distid = in.readString();
        namedist = in.readString();
        city = in.readParcelable(City.class.getClassLoader());

    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(distid);
        out.writeString(namedist);
        out.writeParcelable(city,flags);
    }

    public static final Parcelable.Creator<District> CREATOR
            = new Parcelable.Creator<District>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public District createFromParcel(Parcel in) {
            return new District(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public District[] newArray(int size) {
            return new District[size];
        }
    };



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

    @Override
    public int describeContents() {
        return 0;
    }


}
