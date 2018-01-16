package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhox_ on 24/4/2017.
 */

public class Street implements Parcelable {
    int idduong;
    String tenduong;
    District dist;

    public String getTenduong() {
        return tenduong;
    }

    public void setTenduong(String tenduong) {
        this.tenduong = tenduong;
    }

    public District getDist() {
        return dist;
    }

    public void setDist(District dist) {
        this.dist = dist;
    }

    public int getIdduong() {
        return idduong;
    }

    public void setIdduong(int idduong) {
        this.idduong = idduong;
    }




    public Street(int idduong, String tenduong, District dist) {
        this.idduong = idduong;
        this.tenduong = tenduong;
        this.dist = dist;
    }



    public Street(int idduong,String tenduong){
        this.idduong=idduong;
        this.tenduong=tenduong;
    }

    protected Street(Parcel in) {
        idduong = in.readInt();
        tenduong = in.readString();
        dist = in.readParcelable(District.class.getClassLoader());
    }

    public static final Creator<Street> CREATOR = new Creator<Street>() {
        @Override
        public Street createFromParcel(Parcel in) {
            return new Street(in);
        }

        @Override
        public Street[] newArray(int size) {
            return new Street[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idduong);
        dest.writeString(tenduong);
        dest.writeParcelable(dist,flags);
    }
}
