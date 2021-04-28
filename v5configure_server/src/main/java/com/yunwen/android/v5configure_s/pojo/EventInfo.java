package com.yunwen.android.v5configure_s.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "eventinfo")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class EventInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    private String username;



    private String channelinfo;
    private int type;
    private String date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChannelinfo() {
        return channelinfo;
    }

    public void setChannelinfo(String channelinfo) {
        this.channelinfo = channelinfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
