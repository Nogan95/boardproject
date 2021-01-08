package com.jhin.boardproject.mapper.account;

import com.jhin.boardproject.model.dto.account.MemberDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Select("SELECT * FROM member WHERE memberEmail = #{email}")
    MemberDto getMemberByEmail(@Param("email") String email);

    @Select("SELECT * FROM member WHERE memberId = #{id}")
    MemberDto getMemberById(@Param("id") int id);

    @Insert("INSERT INTO member (memberName, memberEmail, memberPassword, memberStatus) " +
            "VALUES (#{memberDto.memberName},#{memberDto.memberEmail},#{memberDto.memberPassword},#{memberDto.memberStatus})")
    void insertMember(@Param("memberDto") MemberDto memberDto);
}
