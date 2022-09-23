package Ver2.myBoard.reply.service;

import Ver2.myBoard.domain.Reply;
import Ver2.myBoard.reply.dto.ReplyRegisterDto;

import java.util.List;

public interface ReplyService {

    public List<Reply> getListReply(Long id);

    public void saveReply(ReplyRegisterDto replyRegisterDto);

    public void removeReply(Long id);
}
