package web.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.member.LoginController;
import web.entity.Member;
import web.exception.MyException;
import web.model.MemberService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/login")
public class MyServlet extends HttpServlet {
    HashMap<String, Controller> beans;

    @Override
    public void init() throws ServletException {
        try {
            String path = getServletContext().getRealPath("/WEB-INF/beans.xml");
            XmlBeanFactory factory = new XmlBeanFactory(path);
            beans = factory.getBeans();
        } catch (Exception e) {
            e.printStackTrace();
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
        String sign = json.get("sign").getAsString();

        JsonObject returnJson = new JsonObject();

        if(sign!=null){
            Controller controller = beans.get(sign);
            if(controller!=null){

                controller.controllerProcess(request,response, json, returnJson);
            }else{
                returnJson.addProperty("msg","다른값 보내지마세요. 해킹 모니터링중...");
            }
        }else{
            returnJson.addProperty("msg","해킹 모니터링중...");
        }

        out.append(returnJson.toString());
        out.close();

    }
}
