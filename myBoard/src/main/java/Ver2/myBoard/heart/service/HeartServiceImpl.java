package Ver2.myBoard.heart.service;

import Ver2.myBoard.board.repository.BoardRepository;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Heart;
import Ver2.myBoard.domain.HeartStatus;
import Ver2.myBoard.domain.Member;
import Ver2.myBoard.heart.dto.HeartRequestDto;
import Ver2.myBoard.heart.repository.HeartRepository;
import Ver2.myBoard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.ref.ReferenceQueue;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HeartServiceImpl implements HeartService{

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 늘 고민이다 DTO를 Entity로 변환시키는 것을 컨트롤러에서할까 서비스에서 할까?
    // 서비스 계층을 재사용성을 높이고 싶다면 이를 컨트롤러에서 한 뒤에 서비스 계층으로는 Entity를 넘겨주는 것이 맞다.
    // 하지만 서비스계층을 딱히 재사용하지 않는다면 서비스 계층에서 하는 것이 낫다고 본다.
    @Override
    public void addHeart(HeartRequestDto requestDto) {
        //좋아요(like), 싫어요(dislike), 좋아요 취소(removeLike), 싫어요 취소(removeDislike)
        HeartStatus heartStatus = bindingStatus(requestDto.getStatus());

        Member member = memberRepository.findById(requestDto.getMemberId());
        Board board = boardRepository.findById(requestDto.getBoardId());

        Heart heart = Heart.createHeart(heartStatus, member, board);
        heartRepository.save(heart);

        if (heartStatus==HeartStatus.GOOD) {
            board.addGood(); // 좋아요 1 증가
        } else if (heartStatus == HeartStatus.BAD) {
            board.addBad(); // 싫어요 1 증가
        }
    }

    @Override
    public void removeHeart(HeartRequestDto requestDto) {
        HeartStatus heartStatus = bindingStatus(requestDto.getStatus()); // 상태결정
        Board board = boardRepository.findById(requestDto.getBoardId());

        Heart heart = heartRepository.findByMemberIdAndBoardId(requestDto.getMemberId(), requestDto.getBoardId(), heartStatus);
        log.info("heart {}, {}, {}", heart.getHeartStatus(), heart.getBoard().getId(), heart.getMember().getId());
        try {
            heartRepository.remove(heart.getId()); // 추천을 삭제하고
            
            if (heartStatus == HeartStatus.GOOD) {
                board.removeGood(); // 좋아요개수 1빼기
            } else if (heartStatus == HeartStatus.BAD) {
                board.removeBad(); // 싫어요개수 1빼기
            }
        } catch (Exception e) {
            log.info("예외처리 {}", e.getMessage());
        }
    }

    @Override
    public Heart findHeart(HeartRequestDto requestDto) {
        return heartRepository.findByMemberIdAndBoardId(requestDto.getMemberId(), requestDto.getBoardId(),
                bindingStatus(requestDto.getStatus()));
    }

    // 문자열에 따라 상태
    private HeartStatus bindingStatus(String status) {
        if (status.equals("like") || status.equals("removeLike")) {
            return HeartStatus.GOOD;
        } else if (status.equals("dislike") || status.equals("removeDislike")) {
            return HeartStatus.BAD;
        }
        return null;
    }
}
