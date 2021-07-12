package com.yunwen.android.v5configure_s.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "deviceinfo")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Deviceinfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  private String name;
  private String register_id;
  private String device_id;
  private String deviceGroup;  //device_group  deviceGroup

  private String time;
  private String operator_name;
  private String note;
  private String image;


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


  public String getRegister_id() {
    return register_id;
  }

  public void setRegister_id(String register_id) {
    this.register_id = register_id;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getDevice_id() {
    return device_id;
  }

  public void setDevice_id(String device_id) {
    this.device_id = device_id;
  }

  public String getDevice_group() {
    return deviceGroup;
  }

  public void setDevice_group(String device_group) {
    this.deviceGroup = device_group;
  }

  public String getOperator_name() {
    return operator_name;
  }

  public void setOperator_name(String operator_name) {
    this.operator_name = operator_name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
