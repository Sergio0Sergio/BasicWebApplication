package ru.habrahabr.sergiosergio.servlets;

import ru.habrahabr.sergiosergio.accounts.AccountService;
import ru.habrahabr.sergiosergio.accounts.UserProfile;
import ru.habrahabr.sergiosergio.dbService.DBException;
import ru.habrahabr.sergiosergio.dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sgrimanov on 19.10.2016.
 */
public class SignInServlet extends HttpServlet {
    private DBService dbService;
    //private UserProfile account;

    public SignInServlet(DBService dbService){
        this.dbService = dbService;

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //account = accountService.getUserByLogin(login);

        response.setContentType("text/html;charset=utf-8");
        try {
            String name = dbService.getUserByName(login).getName();
        } catch (DBException e) {}
        if ( name != null){

            response.setStatus(401);
            return;
        }
        response.getWriter().println("Authorized: "+login);
        response.setStatus(200);


    }
    }
