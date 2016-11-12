package ru.habrahabr.sergiosergio.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.habrahabr.sergiosergio.accounts.AccountService;
import ru.habrahabr.sergiosergio.accounts.UserProfile;
import ru.habrahabr.sergiosergio.dbService.DBException;
import ru.habrahabr.sergiosergio.dbService.DBService;
import ru.habrahabr.sergiosergio.dbService.dataSets.UsersDataSet;
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

        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("tully");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }
        logger.info("Server started");
    }



        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

        server.join();

}
