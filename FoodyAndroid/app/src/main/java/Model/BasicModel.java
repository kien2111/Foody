package Model;

/**
 * Created by nhox_ on 9/5/2017.
 */

public class BasicModel {
    public BasicModel(String stringid, String stringtext) {
        this.stringid = stringid;
        this.stringtext = stringtext;
    }
    public BasicModel(){}
    public String stringid;
    public String stringtext;

    public BasicModel(int intid, String inttext) {
        this.intid = intid;
        this.inttext = inttext;
    }

    public int intid;
    public String inttext;

    public String getStringid() {
        return stringid;
    }

    public void setStringid(String stringid) {
        this.stringid = stringid;
    }

    public String getStringtext() {
        return stringtext;
    }

    public void setStringtext(String stringtext) {
        this.stringtext = stringtext;
    }

    public int getIntid() {
        return intid;
    }

    public void setIntid(int intid) {
        this.intid = intid;
    }

    public String getInttext() {
        return inttext;
    }

    public void setInttext(String inttext) {
        this.inttext = inttext;
    }



}
