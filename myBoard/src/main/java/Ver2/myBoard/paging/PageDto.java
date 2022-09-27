package Ver2.myBoard.paging;

import lombok.Builder;
import lombok.Data;

@Data
public class PageDto {

    private int pageNum;
    private int amount;
    private Long total;
    private int startPage, endPage;
    private int realEnd;
    private boolean prev, next; // 이전, 다음 페이지 유무

    public PageDto(int pageNum, Long total) {
        this.pageNum = pageNum;
        this.amount = 10;

        this.total = total;
        this.endPage = (int) (Math.ceil(pageNum / 10.0)) * 10;
        this.startPage = this.endPage - 9;

        this.realEnd = (int) (Math.ceil((total * 1.0) / amount));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
