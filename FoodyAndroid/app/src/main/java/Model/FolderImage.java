package Model;

import java.util.ArrayList;

/**
 * Created by nhox_ on 3/5/2017.
 */

public class FolderImage {
    String namefoler;
    ArrayList<ImageInGallery> imageInFolder;

    public FolderImage(){}

    public String getFolder() {
        return namefoler;
    }

    public void setFolder(String folder) {
        this.namefoler = folder;
    }

    public ArrayList<ImageInGallery> getImageInFolder() {
        return imageInFolder;
    }

    public void setImageInFolder(ArrayList<ImageInGallery> imageInFolder) {
        this.imageInFolder = imageInFolder;
    }
}
