package com.spring.mvc.web.member.service;

public interface OAuthService {

    //엑세스 토큰 발급 메서드
    String getAccessToken(String authCode) throws Exception;
}
