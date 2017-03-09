package com.softgroup.vkplayer.data;

import java.io.Serializable;

public class PopularListItem implements Serializable{
    public String title;

    public PopularListItem() {

    }

    public PopularListItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
