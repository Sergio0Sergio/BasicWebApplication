package ru.habrahabr.sergiosergio.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.habrahabr.sergiosergio.accounts.AccountService;
import ru.habrahabr.sergiosergio.accounts.UserProfile;
import ru.habrahabr.sergiosergio.dbService.DBException;
import ru.habrahabr.sergiosergio.dbService.DBService;
import ru.habrahabr.sergiosergio.dbService.dataSets.UsersDataSet;
import ru.habrahabr.sergiosergio.servlets.*;


import javax.servlet.Servlet;
import java.util.logging.Logger;


/**
 * Created by sgrimanov on 07.10.2016.
 */

public class Main {


    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(Main.class.getName());
        Server server = new Server(8080);
//        DBService dbService = new DBService();
//        //dbService.printConnectInfo();
//
//        try {
//            long userId = dbService.addUser("tully", "tully");
//            System.out.println("Added user id: " + userId);
//
//            UsersDataSet dataSet = dbService.getUser(userId);
//            System.out.println("User data set: " + dataSet);
//
//        } catch (DBException e) {
//            e.printStackTrace();
//        }


        ServletContextHandler context = new ServletContextHandler (ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
        //context.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");
        //resource_handler.setWelcomeFiles(new String[]{"index.html"});

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});



        server.setHandler(handlers);

        server.start();
        server.join();
        logger.info("Server started");
    }




}
