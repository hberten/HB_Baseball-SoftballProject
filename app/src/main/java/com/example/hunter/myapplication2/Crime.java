package com.example.hunter.myapplication2;

import java.util.UUID;

/**
 * Created by Hunter on 2/22/2017.
 */

public class Crime {
    private UUID id;
    private String title = "<No Title Set>";

    public Crime() {
        id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
