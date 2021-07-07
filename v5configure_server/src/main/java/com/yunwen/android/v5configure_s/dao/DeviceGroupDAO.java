package com.yunwen.android.v5configure_s.dao;

import com.yunwen.android.v5configure_s.pojo.DeviceGroup;
import com.yunwen.android.v5configure_s.pojo.Deviceinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DeviceGroupDAO extends JpaRepository<DeviceGroup,Integer> {

//    List<DeviceGroup> findByName(String deviceName);

//    boolean removeDeviceInfoById(int id);

//    Deviceinfo findByUuid(String uuid);
}
