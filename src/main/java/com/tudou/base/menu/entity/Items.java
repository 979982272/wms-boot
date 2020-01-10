package com.tudou.base.menu.entity;


import java.util.List;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class Items {
    private String text;
    private String url;
    private List<Items> items;
    private boolean encoded;

    public boolean isEncoded() {
        return encoded;
    }

    public void setEncoded(boolean encoded) {
        this.encoded = encoded;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
