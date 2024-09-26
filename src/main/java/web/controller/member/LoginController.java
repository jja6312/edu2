package web.controller.member;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.Controller;
import web.entity.Member;
import web.exception.MyException;
import web.model.MemberService;

import java.io.IOException;

public class LoginController implements Controller {
    static MemberService memberService;

    public LoginController() {
        try {
            memberService = MemberService.getInstance();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonObject controllerProcess(HttpServletRequest request, HttpServletResponse response
            , JsonObject json, JsonObject returnJson) throws ServletException, IOException {
        String id = json.has("id") ? json.get("id").getAsString() : "null";  // null 체크 추가
        String pw = json.has("pw") ? json.get("pw").getAsString() : "null";  // null 체크 추가
        System.out.println("hihi");
        System.out.println("ID: " + id);
        System.out.println("PW: " + pw);
        try {
            Member member = memberService.login(id, pw);
            System.out.println(member);
            if (member != null) {
                HttpSession session = request.getSession();
                session.setAttribute("member", member);
                returnJson.addProperty("name", member.getName());
            } else {
                returnJson.addProperty("msg", "다시 로그인하세요");
            }
        } catch (MyException e) {
            returnJson.addProperty("msg", "서버에 문제가 발생하였습니다. 다시 로그인하세요");
        }

        return returnJson;

    }
}
