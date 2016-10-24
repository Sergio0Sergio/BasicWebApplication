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
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;
    private UserProfile account;

    public SignInServlet(AccountService accountService){
        this.accountService = accountService;

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        account = accountService.getUserByLogin(login);

        response.setContentType("text/html;charset=utf-8");
        if (accountService.getUserByLogin("login") != null){

            response.setStatus(401);
            return;
        }
        response.getWriter().println("Authorized: "+login);
        response.setStatus(200);


    }
    }
