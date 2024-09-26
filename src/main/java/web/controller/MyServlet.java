package web.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.entity.Member;
import web.exception.MyException;
import web.model.MemberService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class MyServlet extends HttpServlet {
    MemberService memberService;

    @Override
    public void init() throws ServletException {
        try {
            memberService = new MemberService();
        } catch (MyException e) {

            //복구
            System.out.println(e.getCause());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        JsonObject json = (JsonObject) JsonParser.parseReader(request.getReader());
        String sign = json.get("sign").toString();

        JsonObject returnJson = new JsonObject();

        if (sign.equals("login")) {
            try {
                Member member = memberService.login(json.get("id").toString(), json.get("pw").toString());
                if (member != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("member", member);
                    returnJson.addProperty("name",member.getName());
                }else{
                    returnJson.addProperty("msg","다시 로그인하세요");
                }
            } catch (MyException e) {
                returnJson.addProperty("msg","서버에 문제가 발생하였습니다. 다시 로그인하세요");
            }
        } else if (sign.equals("memberInsert")) {

        } else {
            //해킹대응
            System.out.println("sign을 확인하세요.");
        }

        out.append(returnJson.toString());
        out.close();





    }
}
