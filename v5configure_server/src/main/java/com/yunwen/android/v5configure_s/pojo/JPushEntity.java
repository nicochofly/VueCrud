package com.yunwen.android.v5configure_s.pojo;

import java.util.HashMap;
import java.util.Map;

public class JPushEntity {
    String[] platform;
    String[] alias;
    String[] registerid;
    String messageContent;
    String contentType;
    String title;
    HashMap<String, String[]> extras = new HashMap<>();
    public HashMap<String, String[]> getExtras() {
        return extras;
    }

    public void setExtras(HashMap<String, String[]> extras) {
        this.extras = extras;
    }



    public String[] getPlatform() {
        return platform;
    }

    public void setPlatform(String[] platform) {
        this.platform = platform;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public String[] getRegisterid() {
        return registerid;
    }

    public void setRegisterid(String[] registerid) {
        this.registerid = registerid;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
