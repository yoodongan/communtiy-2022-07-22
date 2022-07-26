package com.ll.exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletResponse resp;
    private final HttpServletRequest req;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        try {
            req.setCharacterEncoding("UTF-8");  // 들어오는 데이터를 UTF-8 로 해석하겠다.
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        resp.setCharacterEncoding("UTF-8");  // 완성되는 HTML의 인코딩을 UTF-8로 하겠다.
        resp.setContentType("text/html; charset=utf-8");  // 브라우저에게 만든 결과물이 UTF-8 이라고 알린다.
    }

    public String getParam(String paramName, String defaultValue) {
        String value = req.getParameter(paramName);

        if (value == null || value.trim().length() == 0) {
            return defaultValue;
        }
        return value;
    }

    public int getIntParam(String paramName, int defaultValue) {
        String value = req.getParameter(paramName);

        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }
    public void appendBody(String str) {
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAttr(String name, Object value) {
        req.setAttribute(name, value);
    }

    public void view(String path) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/" + path +".jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMethod() {
        return req.getMethod();
    }

    public String getPath() {
        return req.getRequestURI();
    }


}

