//package com.yunwen.android.v5configure_s.controller
//
//import com.yunwen.android.v5configure_s.pojo.LoginResult
//import com.yunwen.android.v5configure_s.pojo.User
//import com.yunwen.android.v5configure_s.service.UserService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.CrossOrigin
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.ResponseBody
//import org.springframework.web.util.HtmlUtils
//
//
//@Controller
//class LoginController {
//    @Autowired
//    lateinit var userService: UserService
//    @CrossOrigin
//    @PostMapping(value = ["api/login"])
//    @ResponseBody
//     fun login(@RequestBody requestUser: User): LoginResult {
//
//
//         var username = requestUser.username
//         username = HtmlUtils.htmlEscape(username)
//      var  user = this.userService!![username, requestUser.password];
////         if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.password)) {
////             var message = "账号密码错误";
////             System.out.println("test");
////             return LoginResult(400);
////         } else {
////             return LoginResult(200);
////         }
//        return if (null == user) {
//            LoginResult(400);
//        } else {
//            LoginResult(200);
//        }
//     }
//}