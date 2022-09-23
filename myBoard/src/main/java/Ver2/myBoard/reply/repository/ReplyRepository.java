package Ver2.myBoard.reply.repository;

import Ver2.myBoard.domain.Reply;

import java.util.List;

public interface ReplyRepository {

    public List<Reply> getReplyList(Long id);

    public void insertReply(Reply reply);

    public void removeReply(Long id);
}
