package com.jhin.boardproject.model.dto.account;

import lombok.Data;

@Data
public class MemberDto {
    private int memberId;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberStatus;
}
