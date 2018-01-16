package UtilPackage;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Image;
import Model.Meal;

/**
 * Created by nhox_ on 1/4/2017.
 */

public class Utility {
    ///////////////////
    // input :
    // purpose : Mã hóa file ảnh sang Base64
    // output :
    /////////////////////
    public static String encodeImageToBase64(File file){
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        String encodeString =null;
        byte[] bytes = new byte[(int)file.length()];
        try {
            in.read(bytes);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        encodeString = Base64.encodeToString(bytes,Base64.DEFAULT);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeString;
    }
    /////////////
    // input: Mảng các chuỗi
    // purpose: Chuyển từ kiểu mảng sang danh sách
    // output: danh sách các chuỗi
    /////////////
    public static List<String> convertArraytolistString(String[] array){
        List<String> lst = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            lst.add(array[i]);
        }
        return lst;
    }
    /////////////
    // input: Mảng các số nguyên
    // purpose: Chuyển từ kiểu mảng sang danh sách
    // output: danh sách các số nguyên
    /////////////
    public static List<Integer> convertArraytolistInteger(Integer[] array){
        List<Integer> lst = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            lst.add(array[i]);
        }
        return lst;
    }


}
