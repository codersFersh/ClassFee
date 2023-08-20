package com.atbanfei.ssm.controller;

import com.atbanfei.ssm.pojo.Student;
import com.atbanfei.ssm.service.StudentService;
import com.atbanfei.ssm.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/User")
public class LoginUserController {

    @Autowired
    private StudentService studentService;

    /**
     * 根据手机号和密码进行登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody private ResponseEntity<?> Login(@RequestBody Student student, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Student loginUser = studentService.LoginUser(student);
            String token = TokenUtil.login(loginUser.getS_id());
//             返回登录用户信息和Token
             Map<String, Object> result = new HashMap<>();
             result.put("user", loginUser);
             result.put("token", token);
             return ResponseEntity.ok(result);
             }catch (Exception e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }



    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            TokenUtil.logout(token);
        }
        return ResponseEntity.ok("注销成功");
    }

}
