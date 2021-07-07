package com.yunwen.android.v5configure_s.service;

import com.yunwen.android.v5configure_s.dao.PushRecordDAO;
import com.yunwen.android.v5configure_s.pojo.Pushrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushRecordService {

    @Autowired
    PushRecordDAO pushRecordDAO;

    public List<Pushrecord> findAll() {
        return pushRecordDAO.findAll();
    }

    public List<Pushrecord> getAllPushRecord() {
        return pushRecordDAO.findAll();
    }


    public void removePushrecordById(Integer id) {
        pushRecordDAO.deleteById(id);
    }

    public boolean addPushrecord(Pushrecord ci) {
        return pushRecordDAO.save(ci) == null;
    }

    public boolean updateInfo(Pushrecord ci) {
        return (updatePushrecord(ci) != null);
    }

    private Pushrecord updatePushrecord(Pushrecord ci) {
        return pushRecordDAO.save(ci);
    }
}
