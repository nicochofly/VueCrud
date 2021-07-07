package com.yunwen.android.v5configure_s.service;

import com.yunwen.android.v5configure_s.dao.DeviceGroupDAO;
import com.yunwen.android.v5configure_s.pojo.DeviceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceGroupService {

    @Autowired
    DeviceGroupDAO deviceGroupDAO;

//    public List<DeviceGroup> findByhostname(String hostName) {
//        return deviceGroupDAO.f;
//    }

    public List<DeviceGroup> getAllDeviceGroup() {
        return deviceGroupDAO.findAll();
    }


//    public Deviceinfo findByUuid(String uuid) {
//        return deviceInfoDAO.findByUuid(uuid);
//
//    }

    public void removeDeviceGroupById(Integer id) {
        deviceGroupDAO.deleteById(id);
    }

    public boolean addDeviceGroup(DeviceGroup ci) {
        return deviceGroupDAO.save(ci) == null;
    }

    public boolean updateGroup(DeviceGroup ci) {
        return (updateDeviceGroup(ci) != null);
    }

    private DeviceGroup updateDeviceGroup(DeviceGroup ci) {
        return deviceGroupDAO.save(ci);
    }
}
