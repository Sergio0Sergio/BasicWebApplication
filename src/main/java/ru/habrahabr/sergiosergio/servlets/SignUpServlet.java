package ru.habrahabr.sergiosergio.servlets;

import ru.habrahabr.sergiosergio.accounts.AccountService;
import ru.habrahabr.sergiosergio.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by sgrimanov on 19.10.2016.
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;
    public SignUpServlet(AccountService accountService){
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //String email = request.getParameter("email");
        response.setContentType("text/html;charset=utf-8");

        if (login == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            /*if (password == null){
                password = login;
            }*/
            UserProfile profile = new UserProfile(login, password);
            accountService.addNewUser(profile);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
