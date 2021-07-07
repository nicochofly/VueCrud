package com.yunwen.android.v5configure_s.controller;

import com.yunwen.android.v5configure_s.pojo.*;
import com.yunwen.android.v5configure_s.service.EventService;
import com.yunwen.android.v5configure_s.service.PushRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PushRecordController {
    @Autowired
    PushRecordService pushRecordService;

    @CrossOrigin
    @GetMapping(value = "/api/pushrecords")
    @ResponseBody
    public EventResult getPushRecords() {
        List<Pushrecord> result = pushRecordService.getAllPushRecord();

        if (result == null) {
            return new EventResult(200, null);
        } else {
            return new EventResult(200, result.toArray(new EventInfo[result.size()]));
        }
    }



    @CrossOrigin
    @PostMapping(value = "/api/addrecord")
    @ResponseBody
    public RequestResult addEvent(@RequestBody Pushrecord pushrecord) {
        boolean flag = pushRecordService.addPushrecord(pushrecord);
        return new RequestResult(flag ? 200 : 400);
    }
}
