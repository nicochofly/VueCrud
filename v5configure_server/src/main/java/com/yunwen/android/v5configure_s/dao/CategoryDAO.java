package com.yunwen.android.v5configure_s.dao;

import com.yunwen.android.v5configure_s.pojo.Category;
import com.yunwen.android.v5configure_s.pojo.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryDAO extends JpaRepository<Category,Integer> {

//    List<DeviceGroup> findByName(String deviceName);

//    boolean removeDeviceInfoById(int id);

//    Deviceinfo findByUuid(String uuid);
}
