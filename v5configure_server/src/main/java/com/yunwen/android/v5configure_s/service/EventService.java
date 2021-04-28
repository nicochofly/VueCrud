package com.yunwen.android.v5configure_s.service;

import com.yunwen.android.v5configure_s.dao.ChannelDAO;
import com.yunwen.android.v5configure_s.dao.EventDAO;
import com.yunwen.android.v5configure_s.pojo.ChannelInfo;
import com.yunwen.android.v5configure_s.pojo.EventInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventDAO eventDAO;
    public List<EventInfo> getAllEventInfo() {
        return eventDAO.findAll();
    }
    public boolean addEventInfo(EventInfo event) {
        return eventDAO.save(event) != null;
    }
}
