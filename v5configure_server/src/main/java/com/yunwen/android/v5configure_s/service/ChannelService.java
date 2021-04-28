package com.yunwen.android.v5configure_s.service;

import com.yunwen.android.v5configure_s.dao.ChannelDAO;
import com.yunwen.android.v5configure_s.pojo.ChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    ChannelDAO channelDAO;

    public List<ChannelInfo> findByhostname(String hostName) {
        return channelDAO.findByhostname(hostName);
    }

    public List<ChannelInfo> getAllChannelInfo() {
        return channelDAO.findAll();
    }


    public ChannelInfo findByUuid(String uuid) {
        return channelDAO.findByUuid(uuid);

    }

    public void removeChannelInfoById(Integer id) {
        channelDAO.deleteById(id);
    }

    public boolean addChannelInfo(ChannelInfo ci) {
        return channelDAO.save(ci) == null;
    }

    public boolean updateInfo(ChannelInfo ci) {
        return (updateChannelInfo(ci) != null);
    }

    private ChannelInfo updateChannelInfo(ChannelInfo ci) {
        return channelDAO.save(ci);
    }
}
