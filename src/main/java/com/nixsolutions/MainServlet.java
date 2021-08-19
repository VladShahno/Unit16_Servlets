package com.nixsolutions;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    private Map<String, String> userData = new HashMap<>();

    @Override
    public void init() {
        System.out.println("Servlet " + getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">User - Agent Statistic</h1>");

        String iPAddress = req.getRemoteAddr();
        String header = req.getHeader("User-Agent");

        userData.put(iPAddress, header);

        String result;
        responseBody.println("<ul>");

        for (String currentIp : userData.keySet()) {
            if ((result = currentIp).equals(iPAddress)) {
                result = "<b>" + result + " ; " + userData.get(currentIp).toString() + "</b>";
                responseBody.println("<p>IP Address ; User-Agent</p>");
                responseBody.println("<li>" + result + "</li>");
            } else {
                responseBody.println("<p>IP Address; User-Agent</p>");
                responseBody.println("<li>" + userData.toString() + "</li>");
            }
        }
        responseBody.println("</ul>");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + getServletName() + " destroyed");
    }
}