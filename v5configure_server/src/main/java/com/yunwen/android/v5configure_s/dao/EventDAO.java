package com.yunwen.android.v5configure_s.dao;

import com.yunwen.android.v5configure_s.pojo.ChannelInfo;
import com.yunwen.android.v5configure_s.pojo.EventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDAO extends JpaRepository<EventInfo,Integer> {


}
