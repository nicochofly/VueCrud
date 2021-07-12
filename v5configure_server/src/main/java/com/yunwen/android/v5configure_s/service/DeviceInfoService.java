package com.yunwen.android.v5configure_s.service;

import com.yunwen.android.v5configure_s.dao.DeviceInfoDAO;
import com.yunwen.android.v5configure_s.dao.PushContentInfoDAO;
import com.yunwen.android.v5configure_s.pojo.Deviceinfo;
import com.yunwen.android.v5configure_s.pojo.Pushcontent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoService {

    @Autowired
    DeviceInfoDAO deviceInfoDAO;

    public List<Deviceinfo> findByhostname(String hostName) {
        return deviceInfoDAO.findByName("");
    }

    public List<Deviceinfo> getAllDevice() {
        return deviceInfoDAO.findAll();
    }

    public List<Deviceinfo> getDeviceByGroupId(String device_group) {
        return deviceInfoDAO.findByDeviceGroup(device_group);

//        DeviceGroup   >>>  deviceGroup
//        Device_group                   device_group
    }


    public void removeDeviceInfoById(Long id) {
        deviceInfoDAO.deleteById(id);
    }

    public boolean addDeviceinfo(Deviceinfo ci) {
        return deviceInfoDAO.save(ci) == null;
    }

    public boolean updateInfo(Deviceinfo ci) {
        return (updateDeviceinfo(ci) != null);
    }

    private Deviceinfo updateDeviceinfo(Deviceinfo ci) {
        return deviceInfoDAO.save(ci);
    }
}
