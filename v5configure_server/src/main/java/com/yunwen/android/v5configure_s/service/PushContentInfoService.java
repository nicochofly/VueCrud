package com.yunwen.android.v5configure_s.service;

import com.yunwen.android.v5configure_s.dao.ChannelDAO;
import com.yunwen.android.v5configure_s.dao.PushContentInfoDAO;
import com.yunwen.android.v5configure_s.pojo.ChannelInfo;
import com.yunwen.android.v5configure_s.pojo.Pushcontent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushContentInfoService {

    @Autowired
    PushContentInfoDAO pushContentDAO;

    public List<Pushcontent> findByhostname(String hostName) {
        return pushContentDAO.findByName("");
    }

    public List<Pushcontent> getAllPushcontent() {
        return pushContentDAO.findAll();
    }


//    public Pushcontent findByUuid(String uuid) {
//        return pushContentDAO.findByUuid(uuid);
//
//    }

    public void removePushContentById(Integer id) {
        pushContentDAO.deleteById(id);
    }

    public boolean addPushContent(Pushcontent ci) {
        return pushContentDAO.save(ci) == null;
    }

    public boolean updateInfo(Pushcontent ci) {
        return (updatePushContent(ci) != null);
    }

    private Pushcontent updatePushContent(Pushcontent ci) {
        return pushContentDAO.save(ci);
    }
}
