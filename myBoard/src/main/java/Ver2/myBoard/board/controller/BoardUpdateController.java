package Ver2.myBoard.board.controller;

import Ver2.myBoard.board.dto.BoardUpdateDto;
import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.SessionConst;
import Ver2.myBoard.member.dto.MemberLoginDto;
import Ver2.myBoard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardUpdateController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/{id}/edit")
    public String boardUpdateForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) MemberLoginDto loginDto,
                                  @PathVariable("id") Long id, Model model,
                                  RedirectAttributes redirectAttributes) {

        Member member = memberService.getMemberById(loginDto.getId());
        Board board = boardService.getBoardById(id);

        if (!board.checkMemberEquals(member)) {
            redirectAttributes.addFlashAttribute("NotMatching", Boolean.TRUE);
            return "redirect:/board/{id}";
        }
        model.addAttribute("updateBoard", new BoardUpdateDto(id, board.getTitle(), board.getContent()));
        model.addAttribute("nickname", board.getWriter());

        return "board/editForm";
    }

    @PostMapping("/{id}/edit")
    public String boardUpdate(@PathVariable("id") Long id,
                              @Validated @ModelAttribute("updateBoard") BoardUpdateDto updateDto, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "board/editForm";
        }

        try {
            boardService.updateBoard(id, updateDto);
            redirectAttributes.addFlashAttribute("updateStatus", true);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("updateStatus", false);
        }


        return "redirect:/board/{id}";
    }

    @PostMapping("/{id}/remove")
    public String boardRemove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            boardService.removeBoard(id);
            redirectAttributes.addFlashAttribute("removeStatus", true);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("removeStatus", false);
        }

        return "redirect:/board";
    }
}
