package com.yunwen.android.v5configure_s.controller;

import com.yunwen.android.v5configure_s.pojo.ChannelInfo;

import com.yunwen.android.v5configure_s.pojo.ChannelResult;
import com.yunwen.android.v5configure_s.pojo.RequestResult;
import com.yunwen.android.v5configure_s.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @CrossOrigin
    @GetMapping(value = "/api/queryall")
    @ResponseBody
    public ChannelResult getAllChannelInfo() {
        List<ChannelInfo> result = channelService.getAllChannelInfo();

        if (result == null) {
            return new ChannelResult(200, null);
        } else {
            return new ChannelResult(200, result.toArray(new ChannelInfo[result.size()]));
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/append")
    @ResponseBody
    public RequestResult addChannel(@RequestBody ChannelInfo ci) {
        if (ci.getUuid() == null || ci.getUuid().isEmpty()) {
            ci.setUuid(UUID.randomUUID().toString());
        }
        boolean flag = channelService.addChannelInfo(ci);
        return new RequestResult(flag ? 200 : 400);
    }




    @CrossOrigin
    @PostMapping(value = "/api/update")
    @ResponseBody
    public RequestResult updateChannel(@RequestBody ChannelInfo ci) {
        if (ci.getUuid() == null || ci.getUuid().isEmpty()) {
            ci.setUuid(UUID.randomUUID().toString());
        }
        boolean flag = channelService.updateInfo(ci);
        return new RequestResult(flag ? 200 : 400);
    }

    @CrossOrigin
    @DeleteMapping("/api/remove/{id}")
    @ResponseBody
    public RequestResult removeChannel(@PathVariable("id") int id ) {
        try {
            channelService.removeChannelInfoById(id);
            return new RequestResult( 200);
        } catch (Exception e) {
            return new RequestResult( 400);
        }
    }
    @CrossOrigin
    @GetMapping(value = "/api/findbyuuid")
    @ResponseBody
    public ChannelResult getChannelInfoByUuid(@RequestParam("uuid") String uuid) {
        ChannelInfo result = channelService.findByUuid(uuid);
        if (result == null) {
            return new ChannelResult(400, new ChannelInfo[]{});
        } else {
            return new ChannelResult(200, new ChannelInfo[]{result});
        }
    }

}
