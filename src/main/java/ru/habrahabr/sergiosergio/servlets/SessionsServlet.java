package ru.habrahabr.sergiosergio.servlets;

import com.google.gson.Gson;
import ru.habrahabr.sergiosergio.accounts.AccountService;
import ru.habrahabr.sergiosergio.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sg on 16.10.16.
 */
public class SessionsServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionsServlet(AccountService accountService){
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionID = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionID);
        response.setContentType("text/html;charset=utf-8");
        if(profile == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else{
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        response.setContentType("text/html;charset=utf-8");

        if(login == null || pass == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile =accountService.getUserByLogin(login);
        if(profile == null || !profile.getPass().equals(pass)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSession(request.getSession().getId(), profile);
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //sign out
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        response.setContentType("text/html;charset=utf-8");
        if (profile == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
