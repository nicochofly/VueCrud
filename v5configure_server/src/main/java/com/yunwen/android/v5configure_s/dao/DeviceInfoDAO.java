package com.yunwen.android.v5configure_s.dao;

import com.yunwen.android.v5configure_s.pojo.ChannelInfo;
import com.yunwen.android.v5configure_s.pojo.Deviceinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DeviceInfoDAO extends JpaRepository<Deviceinfo,Integer> {

    List<Deviceinfo> findByName(String deviceName);

    List<Deviceinfo> findByDeviceGroup(String device_group);

    boolean removeDeviceInfoById(int id);

//    Deviceinfo findByUuid(String uuid);
}
