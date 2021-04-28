package com.yunwen.android.v5configure_s.dao;

import com.yunwen.android.v5configure_s.pojo.ChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChannelDAO extends JpaRepository<ChannelInfo,Integer> {

    List<ChannelInfo> findByhostname(String hostName);

    boolean removeChannelInfoById(int id);

    ChannelInfo findByUuid(String uuid);
}
