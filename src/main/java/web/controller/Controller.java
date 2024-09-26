package web.controller;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Controller {
    public JsonObject controllerProcess(HttpServletRequest request, HttpServletResponse response , JsonObject json, JsonObject returnJson) throws ServletException, IOException;
}
