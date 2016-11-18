package ru.habrahabr.sergiosergio.servlets;

import org.hibernate.HibernateException;
import ru.habrahabr.sergiosergio.accounts.AccountService;
import ru.habrahabr.sergiosergio.accounts.UserProfile;
import ru.habrahabr.sergiosergio.dbService.DBException;
import ru.habrahabr.sergiosergio.dbService.DBService;
import ru.habrahabr.sergiosergio.dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by sgrimanov on 19.10.2016.
 */
public class SignUpServlet extends HttpServlet {
    private final DBService dbService;
    public SignUpServlet(DBService dbService){
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //String email = request.getParameter("email");
        response.setContentType("text/html;charset=utf-8");

        if (login == null || password == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            /*if (password == null){
                password = login;
            }*/
            //UsersDataSet profile = new UsersDataSet(login, password);
            try {
                dbService.addUser(login, password);
            } catch (Exception e)  {}
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
