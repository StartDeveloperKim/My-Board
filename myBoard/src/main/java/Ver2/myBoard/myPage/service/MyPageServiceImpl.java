package Ver2.myBoard.myPage.service;

import Ver2.myBoard.board.repository.BoardRepository;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.repository.MemberRepository;
import Ver2.myBoard.myPage.dto.MyPageBoardDto;
import Ver2.myBoard.myPage.dto.MyPageMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    @Override
    public MyPageMemberDto getMyPageInfo(String id) {
        Member findMember = memberRepository.findById(id);
        return new MyPageMemberDto(id, findMember.getNickname(), findMember.getCreateDate());
    }

    public List<MyPageBoardDto> getMyPageBoard(String id, int pageNum) {
        List<Board> board = boardRepository.findByMemberId(id, (pageNum - 1) * 10 + 1);

        return board.stream()
                .map(MyPageBoardDto::new)
                .collect(Collectors.toList());
    }
}
