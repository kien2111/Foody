package Model;

import java.util.List;

/**
 * Created by nhox_ on 6/5/2017.
 */

public class RequestImageModel {
    List<String> imagebase64;

    public RequestImageModel(List<String> filename, List<String> imagebase64) {
        this.filename = filename;
        this.imagebase64 = imagebase64;
    }

    List<String> filename;
    public List<String> getImagebase64() {
        return imagebase64;
    }

    public void setImagebase64(List<String> imagebase64) {
        this.imagebase64 = imagebase64;
    }

    public List<String> getFilename() {
        return filename;
    }

    public void setFilename(List<String> filename) {
        this.filename = filename;
    }




}
