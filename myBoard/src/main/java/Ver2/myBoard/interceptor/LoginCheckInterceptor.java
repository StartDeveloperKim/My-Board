package Ver2.myBoard.interceptor;

import Ver2.myBoard.member.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("로그인 인증 인터셉터 시작 {}", requestURI);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자의 요청입니다.");
            response.sendRedirect("/login");
            return false;
        }

        log.info("세션 존재 {}", session.getAttribute(SessionConst.LOGIN_MEMBER));
        return true;
    }
}
