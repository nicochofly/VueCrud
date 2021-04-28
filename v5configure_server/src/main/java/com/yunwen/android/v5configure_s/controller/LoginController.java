package com.yunwen.android.v5configure_s.controller;

import com.yunwen.android.v5configure_s.pojo.RequestResult;
import com.yunwen.android.v5configure_s.pojo.User;
import com.yunwen.android.v5configure_s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public RequestResult login(@RequestBody User requestUser, HttpSession session) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {

            return new RequestResult(400);
        } else {
            session.setAttribute("user", user);
            return new RequestResult(200);
        }
    }

}
