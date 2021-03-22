package com.jingqiduizhang.controller;

import com.jingqiduizhang.pojo.Users;
import com.jingqiduizhang.utils.CookieUtils;
import com.jingqiduizhang.utils.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 加上这个注解 这个控制层接口将不会展示在swagger2文档中。
 */
@ApiIgnore
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Object hello() {
        return "Hello World~";
    }

    @GetMapping("/helloCookie")
    public Object helloCookie(HttpServletRequest request,HttpServletResponse response) {
        Users user = new Users();
        user.setId("123456789");
        user.setId("210312AGRTFRMWM8");
        user.setUsername("duran");
        user.setNickname("duran");
        user.setFace("http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png");
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(user), true);

        return "helloCookie~";
    }
}
