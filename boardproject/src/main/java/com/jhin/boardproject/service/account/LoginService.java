package com.jhin.boardproject.service.account;

import com.jhin.boardproject.mapper.account.MemberMapper;
import com.jhin.boardproject.model.dto.account.MemberDto;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    MemberMapper memberMapper;

    public MemberDto accountCheck(String email, String password) {
        MemberDto member = memberMapper.getMemberByEmail(email);

        if(member == null)
        {
            // 이메일이 존재하지 않음
            return null;
        }
        if(!BCrypt.checkpw(password, member.getMemberPassword()))
        {
            // 패스워드가 일치하지 않음
            return null;
        }
        return member;
    }

    public MemberDto getMember(int id) {
        return memberMapper.getMemberById(id);
    }
}
