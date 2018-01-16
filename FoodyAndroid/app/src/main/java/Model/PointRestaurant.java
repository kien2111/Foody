package Model;

/**
 * Created by nhox_ on 1/4/2017.
 */
/////////////
// input:
// purpose: Model điểm của mỗi người chấm cho 1 quán ăn đổ vào từ database
// output:
/////////////
public class PointRestaurant {
    int userid;
    int restid;
    double point;

    public PointRestaurant(double point, int restid, int userid) {
        this.point = point;
        this.restid = restid;
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRestid() {
        return restid;
    }

    public void setRestid(int restid) {
        this.restid = restid;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }


}
