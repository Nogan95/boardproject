package com.jhin.boardproject.controller.account;

import com.jhin.boardproject.service.account.LoginService;
import com.jhin.boardproject.service.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class RegisterController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @GetMapping(value = "/register")
    public String getRegister() {
        return "/service/register";
    }

    @PostMapping(value ="/register")
    public String confirmRegister(@RequestParam(value="memberEmail") String email,
                               @RequestParam(value="memberName", required=false) String name,
                               @RequestParam(value="memberPassword", required=false) String password) throws Exception{

        System.out.println(email);
        System.out.println(name);
        System.out.println(password);

        registerService.registerMember(email, name, password);
        return "/service/login";
    }
}
