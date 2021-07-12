package com.yunwen.android.v5configure_s.controller;

import com.yunwen.android.v5configure_s.pojo.*;
import com.yunwen.android.v5configure_s.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DeviceController {
    @Autowired
    DeviceInfoService deviceInfoService;

    @CrossOrigin
    @GetMapping(value = "/api/queryalldevice")
    @ResponseBody
    public DeviceInfoResult getAllDeviceInfo() {
        List<Deviceinfo> result = deviceInfoService.getAllDevice();
        if (result == null) {
            return new DeviceInfoResult(200, null);
        } else {
            return new DeviceInfoResult(200, result.toArray(new Deviceinfo[result.size()]));
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/appenddevice")
    @ResponseBody
    public RequestResult addDeviceInfo(@RequestBody Deviceinfo ci) {
        boolean flag = deviceInfoService.addDeviceinfo(ci);
        return new RequestResult(flag ? 400 : 200);
    }


    @CrossOrigin
    @PostMapping(value = "/api/updatedevice")
    @ResponseBody
    public RequestResult updateDeviceInfo(@RequestBody Deviceinfo ci) {
//        if (ci.getUuid() == null || ci.getUuid().isEmpty()) {
//            ci.setUuid(UUID.randomUUID().toString());
//        }
        boolean flag = deviceInfoService.updateInfo(ci);
        return new RequestResult(flag ? 200 : 400);
    }

    @CrossOrigin
    @DeleteMapping("/api/removedevice/{id}")
    @ResponseBody
    public RequestResult removeDeviceInfo(@PathVariable("id") Long id) {
        try {
            System.out.println(">>>>>>>>>>>"+id);
            deviceInfoService.removeDeviceInfoById(id);
            return new RequestResult(200);
        } catch (Exception e) {
            return new RequestResult(400);
        }
    }



    @CrossOrigin
    @GetMapping(value = "/api/finddevicebygroupname")
    @ResponseBody
    public DeviceInfoResult getDeviceInfobyGroupName(@RequestParam("group_name") String groupName) {
        System.out.println(">>>>>>groupName >>>>>"+groupName);
        List<Deviceinfo> result = deviceInfoService.getDeviceByGroupId(groupName);
        System.out.println(">>>>>>"+(result == null));

        System.out.println(">>>>>>result.size   "+(result.size()));
        for(Deviceinfo device:result)
        {
            System.out.println(device.getName()+"  "+device.getDevice_group()+"  "+device.getId());
        }
        if (result == null) {
            return new DeviceInfoResult(200, null);
        } else {
            return new DeviceInfoResult(200, result.toArray(new Deviceinfo[result.size()]));
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
