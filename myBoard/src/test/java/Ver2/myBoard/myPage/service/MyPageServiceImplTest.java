package Ver2.myBoard.myPage.service;

import Ver2.myBoard.myPage.dto.MyPageMemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyPageServiceImplTest {

    @Autowired
    MyPageService myPageService;

    @Test
    @DisplayName("마이페이지 서비스 테스트")
    void MyPageTest() {
        String id = "lee";
        MyPageMemberDto myPageInfo = myPageService.getMyPageInfo(id);

        System.out.println("myPageInfo.toString() = " + myPageInfo.toString());
    }
}