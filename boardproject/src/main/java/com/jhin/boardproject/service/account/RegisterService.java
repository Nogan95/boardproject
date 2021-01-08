package com.jhin.boardproject.service.account;

import com.jhin.boardproject.mapper.account.MemberMapper;
import com.jhin.boardproject.model.dto.account.MemberDto;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    MemberMapper memberMapper;

    public void registerMember(String email, String password, String name){
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberEmail(email);
        memberDto.setMemberPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
        memberDto.setMemberName(name);
        memberDto.setMemberStatus("A");

        memberMapper.insertMember(memberDto);
    }
}
