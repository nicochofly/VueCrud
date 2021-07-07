package com.yunwen.android.v5configure_s.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "pushcontent")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Pushcontent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;
    private String content;
    private long type_id;
    private String type_name;
    private long category_id;


    private String category;
    private String date;
    private long operatorId;
    private String operator_name;
    private String resource_path;

    private long expirationdate_start;
    private long expirationdate_end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getType_id() {
        return type_id;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }


    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getResource_path() {
        return resource_path;
    }

    public void setResource_path(String resource_path) {
        this.resource_path = resource_path;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getExpirationdate_start() {
        return expirationdate_start;
    }

    public void setExpirationdate_start(long expirationdate_start) {
        this.expirationdate_start = expirationdate_start;
    }

    public long getExpirationdate_end() {
        return expirationdate_end;
    }

    public void setExpirationdate_end(long expirationdate_end) {
        this.expirationdate_end = expirationdate_end;
    }
}
