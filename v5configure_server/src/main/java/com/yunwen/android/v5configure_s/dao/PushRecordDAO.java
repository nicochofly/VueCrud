package com.yunwen.android.v5configure_s.dao;

import com.yunwen.android.v5configure_s.pojo.ChannelInfo;
import com.yunwen.android.v5configure_s.pojo.Pushrecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PushRecordDAO extends JpaRepository<Pushrecord,Integer> {


}
