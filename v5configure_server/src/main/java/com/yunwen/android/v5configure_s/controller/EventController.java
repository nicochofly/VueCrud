package com.yunwen.android.v5configure_s.controller;

import com.yunwen.android.v5configure_s.pojo.*;
import com.yunwen.android.v5configure_s.service.EventService;
import com.yunwen.android.v5configure_s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class EventController {
    @Autowired
    EventService eventService;

    @CrossOrigin
    @GetMapping(value = "/api/events")
    @ResponseBody
    public EventResult getEvents() {
        List<EventInfo> result = eventService.getAllEventInfo();

        if (result == null) {
            return new EventResult(200, null);
        } else {
            return new EventResult(200, result.toArray(new EventInfo[result.size()]));
        }
    }



    @CrossOrigin
    @PostMapping(value = "/api/addevent")
    @ResponseBody
    public RequestResult addEvent(@RequestBody EventInfo event) {
        boolean flag = eventService.addEventInfo(event);
        return new RequestResult(flag ? 200 : 400);
    }
}
