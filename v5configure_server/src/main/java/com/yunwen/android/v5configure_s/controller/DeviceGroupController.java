package com.yunwen.android.v5configure_s.controller;

import com.yunwen.android.v5configure_s.pojo.*;
import com.yunwen.android.v5configure_s.service.DeviceGroupService;
import com.yunwen.android.v5configure_s.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DeviceGroupController {
    @Autowired
    DeviceGroupService deviceGroupService;

    @CrossOrigin
    @GetMapping(value = "/api/queryalldevicegroup")
    @ResponseBody
    public DeviceGroupResult getAllDeviceGroup() {
        List<DeviceGroup> result = deviceGroupService.getAllDeviceGroup();
        if (result == null) {
            return new DeviceGroupResult(200, null);
        } else {
            return new DeviceGroupResult(200, result.toArray(new DeviceGroup[result.size()]));
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/appenddevicegroup")
    @ResponseBody
    public RequestResult addDeviceGroup(@RequestBody DeviceGroup ci) {
        boolean flag = deviceGroupService.addDeviceGroup(ci);
        return new RequestResult(flag ? 400 : 200);
    }


    @CrossOrigin
    @PostMapping(value = "/api/updatedevicegroup")
    @ResponseBody
    public RequestResult updateDeviceGroup(@RequestBody DeviceGroup ci) {
//        if (ci.getUuid() == null || ci.getUuid().isEmpty()) {
//            ci.setUuid(UUID.randomUUID().toString());
//        }
        boolean flag = deviceGroupService.updateGroup(ci);
        return new RequestResult(flag ? 200 : 400);
    }

    @CrossOrigin
    @DeleteMapping("/api/removedevicegroup/{id}")
    @ResponseBody
    public RequestResult removeDeviceGroup(@PathVariable("id") Long id) {
        try {
            deviceGroupService.removeDeviceGroupById(id);
            return new RequestResult(200);
        } catch (Exception e) {
            return new RequestResult(400);
        }
    }

//    @CrossOrigin
//    @GetMapping(value = "/api/finddevicebyuuid")
//    @ResponseBody
//    public ChannelResult getDeviceByUuid(@RequestParam("uuid") String uuid) {
//        ChannelInfo result = pushContentService.findByUuid(uuid);
//        if (result == null) {
//            return new ChannelResult(400, new ChannelInfo[]{});
//        } else {
//        return new ChannelResult(200, new ChannelInfo[]{result});
//        }
//        return null;
//    }

}
