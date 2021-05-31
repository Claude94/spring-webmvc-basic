package com.spring.mvc.web.reply.repository;

import com.spring.mvc.web.common.paging.Criteria;
import com.spring.mvc.web.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 입력 기능
    int save(Reply reply);

    //댓글 수정
    int update(Reply reply);

    //댓글 삭제
    int delete(int replyNo);

    //댓글 한개 조회
    Reply read(int replyNo);

    //특정 계시글에 댓글 목록 조회

    /**
     * 마이바티스 매퍼는 기본적으로 1개의 매개값만 받을 수 있습니다
     * 그러나 @Pram을 통해 여러개를 처리할 수 있습니다
     * */

    List<Reply> getList(@Param("bno") int boardNo, @Param("cri") Criteria cri);

    //특정 게시글에 댓글 총 개수 조회
    int getCount(int boardNo);
}
