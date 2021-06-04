package com.spring.mvc.web.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

//인터셉터 : 컨트롤러에 요철이 들어가기 전, 후에 공통으로 처리할 일들을
//           정해놓은 역할의 클래스(ex: 권한 검증, 자동로그인 검증 등)
@Log4j2
@Configuration
public class AuthInterceptor implements HandlerInterceptor {

    /*
    * 회원 인증이 필요한 페이지에 진입할 때 사전 인증 검사를 수행하기 위해서는
    * 사전처리 메서드 프리헨들을 오버라이딩 해야함
    *
    * 프리핸들의 리턴값이 트루일 경우 컨트롤러 진입을 허용하여 펄스일 경우 진입을 허용하지 않음
    * */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.info("인텁셉터 작동 : 회원 검증");
        HttpSession session = request.getSession();

        //로그인을 안했을 경우
        if (session.getAttribute("loginUser") == null) {
            response.sendRedirect("/member/sign-in");
            return false;
        }
        return true;
    }
}
