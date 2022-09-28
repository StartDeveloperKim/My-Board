package Ver2.myBoard.myPage.service;

import Ver2.myBoard.myPage.dto.MyPageBoardDto;
import Ver2.myBoard.myPage.dto.MyPageMemberDto;

import java.util.List;

public interface MyPageService {

    public MyPageMemberDto getMyPageInfo(String id);

    public List<MyPageBoardDto> getMyPageBoard(String id, int pageNum);
}
