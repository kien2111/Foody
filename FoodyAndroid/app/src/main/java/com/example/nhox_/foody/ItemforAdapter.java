package com.example.nhox_.foody;

import java.util.HashMap;
import java.util.List;

import Model.District;
import Model.Image;

/**
 * Created by nhox_ on 2/4/2017.
 */

public class ItemforAdapter{
    public ItemforAdapter(String text,Integer source){
        icon = source;
        rawtext = text;
    }
    public ItemforAdapter(String text,String anothertext){
        this.anothertext=anothertext;
        rawtext = text;
    }
    public ItemforAdapter(District dist){
        this.dist = dist;
    }
    public ItemforAdapter(Image image){
        this.image = image;
    }
    HashMap<String,String> hm;
    Image image;
    District dist;
    String anothertext;
    Integer icon;
    String rawtext;
}