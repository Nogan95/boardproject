package com.jhin.boardproject.handler.interceptor;

import com.jhin.boardproject.model.dto.account.MemberDto;
import com.jhin.boardproject.service.account.AuthCookieService;
import com.jhin.boardproject.service.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    AuthCookieService authCookieService;

    @Autowired
    LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession();
        if(session.getAttribute("authInfo") == null) {
            if(request.getCookies()!=null)
            {
                for(Cookie cookie : request.getCookies())
                {
                    if(cookie.getName().equals("authCookie"))
                    {
                        int memberId = authCookieService.getMemeberIdByCookie(cookie.getValue());
                        MemberDto member = loginService.getMember(memberId);

                        session.setAttribute("authInfo", member);
                        session.setAttribute("authName", member.getMemberName());

                        return true;
                    }
                }
            }

            // 로그인하지 않은 사용자일 경우 로그인 페이지로 이동
            response.sendRedirect("/account/login");
            return false;
        }

        // 로그인한 사용자일 경우 Controller 호출
        return true;
    }
}
