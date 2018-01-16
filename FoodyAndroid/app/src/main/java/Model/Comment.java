package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model bình luận để đổ dữ liệu từ database vào
// output:
/////////////
public class Comment implements Parcelable {
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

    Restaurant rest;
    User user;
    String comment;



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Comment(Restaurant rest, User user, String comment) {
        this.rest = rest;
        this.user = user;
        this.comment = comment;
    }

    public Comment(Parcel in){
        rest = in.readParcelable(Restaurant.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
        comment = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<Comment> CREATOR
            = new Parcelable.Creator<Comment>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(user,flags);
        out.writeParcelable(rest,flags);
        out.writeString(comment);
    }
}
