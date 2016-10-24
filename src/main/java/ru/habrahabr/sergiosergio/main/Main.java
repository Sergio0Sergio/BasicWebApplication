package ru.habrahabr.sergiosergio.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.habrahabr.sergiosergio.accounts.AccountService;
import ru.habrahabr.sergiosergio.accounts.UserProfile;
import ru.habrahabr.sergiosergio.servlets.SignInServlet;
import ru.habrahabr.sergiosergio.servlets.SignUpServlet;
import ru.habrahabr.sergiosergio.servlets.UsersServlet;

import java.util.logging.Logger;


/**
 * Created by sgrimanov on 07.10.2016.
 */

public class Main {


    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(Main.class.getName());

        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        logger.info("Server started");
        server.join();
    }
}
