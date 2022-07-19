package com.ssm.controller;

import com.ssm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    private static final Map<String, Map<String, String>> userInfoMap = new HashMap<>();
    private static String currentUser = null;

    static {
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("password", "admin");
        infoMap.put("login", "no");
        infoMap.put("avatar", "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        userInfoMap.put("admin", infoMap);
    }

    @PostMapping(value = "api/login/account")
    @ResponseBody
    public Map<String, Boolean> login(@RequestBody User user) {
        String userName = user.getUsername();
        String userPassword = user.getPassword();
        Map<String, Boolean> respondMap = new HashMap<>();
        respondMap.put("success", false);
        if (userInfoMap.containsKey(userName) && userInfoMap.get(userName).get("password").equals(userPassword)) {
            respondMap.put("success", true);
            userInfoMap.get(userName).put("login", "yes");
            currentUser = userName;
        }
        return respondMap;
    }

    @GetMapping("api/currentUser")
    @ResponseBody
    public Map<String, Object> getCurrentUser() {
        Map<String, Object> respondMap = new HashMap<>();
        respondMap.put("success", false);
        Map<String, String> data = new HashMap<>();
        respondMap.put("data", data);
        if (currentUser != null) {
            respondMap.put("success", true);
            data.put("name", currentUser);
            data.put("avatar", userInfoMap.get(currentUser).get("avatar"));
        }
        return respondMap;
    }

    @GetMapping("api/login/outLogin")
    @ResponseBody
    public Map<String, Boolean> logout(@RequestParam(value = "username") String userName) {
        Map<String, Boolean> respondMap = new HashMap<>();
        respondMap.put("success", false);
        userInfoMap.get(userName).put("login", "no");
        if (userName.equals(currentUser)) {
            currentUser = null;
        }
        respondMap.put("success", true);
        return respondMap;
    }

    @GetMapping("/")
    public String index() {
        if (currentUser != null) {
            return "record";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/record")
    public String main() {
        if (currentUser != null) {
            return "record";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/upload")
    public String upload() {
        if (currentUser != null) {
            return "upload";
        } else {
            return "redirect:/login";
        }
    }

}
