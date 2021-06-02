package com.spring.mvc.web.member.repository;

import com.spring.mvc.web.member.domain.Auth;
import com.spring.mvc.web.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper mapper;

    @Test
    @DisplayName("회원가입에 성공")
    void registerTest() {
        Member member = Member.builder()
                .account("abc4321")
                .password("ass1234")
                .name("고길동")
                .auth(Auth.COMMON)
                .email("qweqwe@gmail.com")
                .build();

        System.out.println("member = " + member);

        mapper.register(member);

        Member findUser = mapper.getUser(member.getAccount());
    }

    @Test
    @DisplayName("아이디, 이메일 중복확인")
    void duplicateTest() {
        String inputAccount = "abc4321";
        Map<String, Object> datas = new HashMap<>();
        datas.put("type", "account");
        datas.put("keyword",inputAccount);

        int result = mapper.isDuplicate(datas);

        assertTrue(result == 1);
    }

    @Test
    @DisplayName("비밀번호가 암호화 되어 있어야함")
    void encodeTest() {
        //평문 페스워드
        String rawPw = "ddd5555";

        //패스워드 인코딩
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPw = encoder.encode(rawPw);

        System.out.println("==========================================");
        System.out.println("평문 :" + rawPw);
        System.out.println("암호문 :" + encoderPw);
    }

    @Test
    @DisplayName("비밀번호가 암호화 된 상태로 회원가입")
    void regTest2() {
        Member member = Member.builder()
                .account("banana")
                .email("sadas@naver.com")
                .name("김바나나")
                .auth(Auth.COMMON)
                .password(new BCryptPasswordEncoder().encode("adasd1234"))
                .build();

        mapper.register(member);
    }

    @Test
    @DisplayName("로그인 검증을 수행")
    void loginTest() {
        //로그인 시도 아이디
        String inputId = "banana";
        //로그인 시도 패스워드
        String inputPw = "adasd1234";

        //로그인 시도 아이디를 기반으로 회원정보를 조회
        Member member = mapper.getUser(inputId);

        if (member != null) {
            //db에 저장된 비번
            String dbPw = member.getPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공");
            } else {
                System.out.println("로그인 실패");
            }
        } else {
            System.out.println("회원가입을 진행");
        }
    }
}