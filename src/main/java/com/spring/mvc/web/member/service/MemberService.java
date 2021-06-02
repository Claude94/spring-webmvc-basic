package com.spring.mvc.web.member.service;

import com.spring.mvc.web.member.domain.Member;
import com.spring.mvc.web.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    //회원가입
    public void signUp(Member member) {
/*        String rp = member.getPassword();
        member.setPassword(new BCryptPasswordEncoder().encode(rp));*/

        memberMapper.register(member);

    }
    /**
     * type 중복검사유형 (계정, 이메일)
     * keyword 중복검사값
     * return 중복되었으면 ture, 중복되지 않았으면 false
     * */
    //중복 확인
    public boolean isDuplicate(String type, String keyword) {
        Map<String, Object> checkDataMap = new HashMap<>();
        checkDataMap.put("type", type);
        checkDataMap.put("keyword", keyword);

        return memberMapper.isDuplicate(checkDataMap) == 1;
    }

    //회원 정보 조회 기능
    public Member getMember(String account) {
        return memberMapper.getUser(account);
    }

}
