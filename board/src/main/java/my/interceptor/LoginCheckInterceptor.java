package my.interceptor;

import lombok.extern.slf4j.Slf4j;
import my.member.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    /** HandlerInterceptor<br>
     * preHandle : 컨트롤러 호출 전에 호출된다. -> 인증은 preHandle만 사용하면된다.<br>
     * postHandle : 컨트롤러에서 예외가 발생하면 postHandle은 호출되지 않는다.<br>
     * afterCompletion : afterCompletion은 항상 호출된다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("로그인 인증체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자의 요청입니다.");
            response.sendRedirect("/member/login?redirectURL=" + requestURI);
            return false;
        }

        log.info("세션 존재 {}", session.getAttribute(SessionConst.LOGIN_MEMBER));

        return true; // true가 호출되면 다음 인터셉터나 컨트롤러가 호출된다.
    }
}
