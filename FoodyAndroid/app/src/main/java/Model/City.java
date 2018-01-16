package Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model thành phố và tỉnh để đổ dữ liệu vào
// output:
/////////////
public class City implements Parcelable,Serializable,KvmSerializable {

    String cityid;
    String namecity;

    public City(String cityid, String namecity) {
        this.cityid = cityid;
        this.namecity = namecity;
    }
    public City(){

    }

    public String getNamecity() {
        return namecity;
    }

    public void setNamecity(String namecity) {
        this.namecity = namecity;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public City(Parcel in) {
        cityid = in.readString();
        namecity = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(cityid);
        out.writeString(namecity);
    }

    public static final Parcelable.Creator<City> CREATOR
            = new Parcelable.Creator<City>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return cityid;
            case 1:
                return namecity;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch(i)
        {
            case 0:
                cityid = o.toString();
                break;
            case 1:
                namecity = o.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch(i)
        {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "cityid";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "namecity";
                break;
            default:break;
        }
    }
}
