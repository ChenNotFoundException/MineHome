package com.cc.MineHome.POJO;

/**
 * Author:ChenChen
 * Date:2020/2/16
 * Description:
 */
public class MineShare {
    private String date;
    private String picture;
    private String describle;

    public MineShare(String date, String picture, String describle) {
        this.date = date;
        this.picture = picture;
        this.describle = describle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }
}
