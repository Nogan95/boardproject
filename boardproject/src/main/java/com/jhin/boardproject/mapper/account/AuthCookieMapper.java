package com.jhin.boardproject.mapper.account;

import org.apache.ibatis.annotations.*;

@Mapper
public interface AuthCookieMapper {
    @Select("SELECT memberId from authCookie WHERE cookie = #{cookie}")
    int getMemberId(@Param("cookie") String cookie);

    @Insert("INSERT INTO authCookie (cookie, memberId, expires) VALUES (#{cookie}, #{id}, NOW() + INTERVAL 24 HOUR)")
    void insertAuthCookie(@Param("cookie") String cookie, @Param("id") int id);

    @Delete("DELETE FROM authCookie WHERE expires < NOW()")
    void deleteExpired();

    @Delete("DELETE FROM authCookie WHERE memberId=#{id}")
    void deleteCookieById(@Param("id")int id);
}
