package Ver2.myBoard.reply.service;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.repository.BoardRepository;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Member;
import Ver2.myBoard.domain.Reply;
import Ver2.myBoard.member.repository.MemberRepository;
import Ver2.myBoard.reply.dto.ReplyRegisterDto;
import Ver2.myBoard.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Reply> getListReply(Long id) {
        return replyRepository.getReplyList(id);
    }

    @Override
    public void saveReply(ReplyRegisterDto replyRegisterDto) {
        Board board = boardRepository.findById(replyRegisterDto.getBoardId());
        Member member = memberRepository.findById(replyRegisterDto.getMemberId());
        log.info("멤버 :{}", member);
        Reply reply = Reply.createReply(member, board, replyRegisterDto);

        replyRepository.insertReply(reply);
    }

    @Override
    public void removeReply(Long id) {
        replyRepository.removeReply(id);
    }
}
