package com.cc.MineHome.POJO;

/**
 * Author:ChenChen
 * Date:2020/2/16
 * Description:
 */
public class Content {
    private String username;
    private Integer user_icon;
    private Integer shared_pic;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(Integer user_icon) {
        this.user_icon = user_icon;
    }

    public Integer getShared_pic() {
        return shared_pic;
    }

    public void setShared_pic(Integer shared_pic) {
        this.shared_pic = shared_pic;
    }

    public Content(String username, Integer picture, Integer shared_pic) {
        this.username = username;
        this.user_icon = picture;
        this.shared_pic = shared_pic;
    }
}
