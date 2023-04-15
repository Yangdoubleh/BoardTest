package spring.board.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {

    @RequestMapping("/board/main")
    public String boardList(HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info("LoginId = {}", session.getAttribute("loginId"));
        return "/board/boardList";
    }
}
