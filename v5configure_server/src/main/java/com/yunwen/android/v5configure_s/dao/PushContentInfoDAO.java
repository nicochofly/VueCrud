package com.yunwen.android.v5configure_s.dao;

import com.yunwen.android.v5configure_s.pojo.Deviceinfo;
import com.yunwen.android.v5configure_s.pojo.Pushcontent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PushContentInfoDAO extends JpaRepository<Pushcontent,Integer> {

    List<Pushcontent> findByName(String pushcontentName);

    boolean removePushcontentById(int id);

//    Pushcontent findByUuid(String uuid);
}
