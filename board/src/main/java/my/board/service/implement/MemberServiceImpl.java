package my.board.service.implement;

import lombok.RequiredArgsConstructor;
import my.board.domain.Member;
import my.board.repository.interfaces.MemberRepository;
import my.board.service.interfaces.MemberService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(Member member) {
        memberRepository.insert(member);
    }

    @Override
    public Member selectById(String id) {
        return memberRepository.selectById(id);
    }
}
