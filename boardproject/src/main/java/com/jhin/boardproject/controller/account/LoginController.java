package com.jhin.boardproject.controller.account;

import com.jhin.boardproject.model.dto.account.MemberDto;
import com.jhin.boardproject.service.account.AuthCookieService;
import com.jhin.boardproject.service.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.jhin.boardproject.util.RandomHashString.getRandomHashString;

@Controller
@RequestMapping("/account")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthCookieService authCookieService;


    @GetMapping(value = "/login")
    public String getLogin() {
        return "/service/login";
    }

    @GetMapping(value = "/logout")
    public String getLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        authCookieService.deleteAuthCookie(((MemberDto)session.getAttribute("authInfo")).getMemberId());
        session.invalidate();
        return "/service/login";
    }

    @PostMapping("/login")
    public String confirmLogin(HttpServletResponse response,
                               HttpServletRequest request,
                               @RequestParam(value="memberEmail") String email,
                               @RequestParam(value="memberPassword") String password,
                               @RequestParam(value="useCookie") Boolean useRemember) throws Exception {

        // password Crypting
        MemberDto member = loginService.accountCheck(email, password);
        if(member != null) {
            String cookieString = getRandomHashString();

            HttpSession session = request.getSession();
            session.setAttribute("authInfo", member);
            session.setAttribute("authName", member.getMemberName());

            if(useRemember)
            {
                Cookie authCookie = new Cookie("authCookie", cookieString);
                authCookie.setPath("/");
                authCookie.setMaxAge(useRemember?60*60*24*7:0);
                authCookieService.insertAuthCookie(cookieString, member.getMemberId());
                response.addCookie(authCookie);
            }

            return "index";
        }
        else {
            return null;
        }
    }
}
