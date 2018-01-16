package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhox_ on 3/5/2017.
 */

public class ImageInGallery implements Parcelable {
    String path;
    boolean isSelected;

    public ImageInGallery(String path, boolean isSelected) {
        this.path = path;
        this.isSelected = isSelected;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ImageInGallery(Parcel dest){
        this.path=dest.readString();
        this.isSelected=(dest.readInt()==0)? false : true;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeInt(isSelected ? 1 : 0);
    }
    public static final Parcelable.Creator<ImageInGallery> CREATOR = new Parcelable.Creator<ImageInGallery>()
    {
        public ImageInGallery createFromParcel(Parcel in)
        {
            return new ImageInGallery(in);
        }
        public ImageInGallery[] newArray(int size)
        {
            return new ImageInGallery[size];
        }
    };
}
