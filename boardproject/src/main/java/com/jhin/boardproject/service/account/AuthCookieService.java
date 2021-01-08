package com.jhin.boardproject.service.account;

import com.jhin.boardproject.mapper.account.AuthCookieMapper;
import com.jhin.boardproject.mapper.account.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthCookieService {
    @Autowired
    AuthCookieMapper authCookieMapper;

    public int getMemeberIdByCookie(String cookie){
        authCookieMapper.deleteExpired();
        return authCookieMapper.getMemberId(cookie);
    }

    public void insertAuthCookie(String cookie, int id){
        authCookieMapper.insertAuthCookie(cookie, id);
    }

    public void deleteAuthCookie(int id){
        authCookieMapper.deleteCookieById(id);
    }
}
