<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    const msg = '${result}';

    if (msg === 'idFail') {
        alert("회원가입을 먼저 해주세요");
        // location.href='/member/sigin-up'
        history.back(); //뒤로가기

    } else if (msg === 'pwFail') {
        alert("비밀번호 틀림");
        history.back(); //뒤로가기
    }
</script>