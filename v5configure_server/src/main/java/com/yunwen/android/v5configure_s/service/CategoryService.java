package com.yunwen.android.v5configure_s.service;

import com.yunwen.android.v5configure_s.dao.CategoryDAO;
import com.yunwen.android.v5configure_s.dao.DeviceGroupDAO;
import com.yunwen.android.v5configure_s.pojo.Category;
import com.yunwen.android.v5configure_s.pojo.DeviceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

//    public List<DeviceGroup> findByhostname(String hostName) {
//        return deviceGroupDAO.f;
//    }

    public List<Category> getAllCategory() {
        return categoryDAO.findAll();
    }


//    public Deviceinfo findByUuid(String uuid) {
//        return deviceInfoDAO.findByUuid(uuid);
//
//    }

    public void removeCategoryById(Integer id) {
        categoryDAO.deleteById(id);
    }

    public boolean addCategory(Category ci) {
        return categoryDAO.save(ci) == null;
    }

    public boolean updateCategory(Category ci) {
        return (updateCategory2DB(ci) != null);
    }

    private Category updateCategory2DB(Category ci) {
        return categoryDAO.save(ci);
    }
}
