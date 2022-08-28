package my.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    private int startPage; // 시작페이지
    private int endPage; // 끝 페에지
    private boolean prev, next; // 다음과 이전이 있는지 판단

    private int total;
    private Criteria cri;

    public Page(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        this.startPage = this.endPage - 9;

        int realEnd = (int) (Math.ceil((total * 1.0 / cri.getAmount())));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
