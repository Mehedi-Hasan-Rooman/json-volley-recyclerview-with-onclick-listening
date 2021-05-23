package com.example.json_volley;

public class Example_item {

    String imageurl;
    String creator_name;
    int likes;

    public Example_item(String imageurl, String creator_name, int likes) {
        this.imageurl = imageurl;
        this.creator_name = creator_name;
        this.likes = likes;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public int getLikes() {
        return likes;
    }
}
