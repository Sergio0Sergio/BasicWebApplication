package ru.habrahabr.sergiosergio.servlets;

import ru.habrahabr.sergiosergio.accounts.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sgrimanov on 19.10.2016.
 */
public class SignInServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    AccountService accountService = new AccountService();
}
