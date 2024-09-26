package web.controller.member;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.exception.MyException;
import web.model.MemberService;

import java.io.IOException;

public class MemberInsertController implements Controller {
    private MemberService memberService;

    public MemberInsertController() {
        try {
            memberService.getInstance();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonObject controllerProcess(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject returnJson) throws ServletException, IOException {
        return null;
    }
}
