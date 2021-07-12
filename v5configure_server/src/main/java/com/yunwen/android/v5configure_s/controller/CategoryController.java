package com.yunwen.android.v5configure_s.controller;

import com.yunwen.android.v5configure_s.pojo.*;
import com.yunwen.android.v5configure_s.service.CategoryService;
import com.yunwen.android.v5configure_s.service.DeviceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @CrossOrigin
    @GetMapping(value = "/api/queryallcategory")
    @ResponseBody
    public CategoryResult getAllCategory() {
        List<Category> result = categoryService.getAllCategory();
        if (result == null) {
            return new CategoryResult(200, null);
        } else {
            return new CategoryResult(200, result.toArray(new Category[result.size()]));
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/appendcategory")
    @ResponseBody
    public RequestResult addCategory(@RequestBody Category ci) {
        boolean flag = categoryService.addCategory(ci);
        return new RequestResult(flag ? 400 : 200);
    }


    @CrossOrigin
    @PostMapping(value = "/api/updatecategory")
    @ResponseBody
    public RequestResult updateCategory(@RequestBody Category ci) {
//        if (ci.getUuid() == null || ci.getUuid().isEmpty()) {
//            ci.setUuid(UUID.randomUUID().toString());
//        }
        boolean flag = categoryService.updateCategory(ci);
        return new RequestResult(flag ? 200 : 400);
    }

    @CrossOrigin
    @DeleteMapping("/api/removecategory/{id}")
    @ResponseBody
    public RequestResult removeCategory(@PathVariable("id") Long id) {
        try {
            categoryService.removeCategoryById(id);
            return new RequestResult(200);
        } catch (Exception e) {
            return new RequestResult(400);
        }
    }

//    @CrossOrigin
//    @GetMapping(value = "/api/finddevicebyuuid")
//    @ResponseBody
//    public ChannelResult getDeviceByUuid(@RequestParam("uuid") String uuid) {
//        ChannelInfo result = pushContentService.findByUuid(uuid);
//        if (result == null) {
//            return new ChannelResult(400, new ChannelInfo[]{});
//        } else {
//        return new ChannelResult(200, new ChannelInfo[]{result});
//        }
//        return null;
//    }

}
