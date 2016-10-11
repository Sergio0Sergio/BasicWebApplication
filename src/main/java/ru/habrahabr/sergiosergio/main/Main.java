package ru.habrahabr.sergiosergio.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import ru.habrahabr.sergiosergio.servlets.AllRequestsServlet;
import ru.habrahabr.sergiosergio.servlets.MirrorRequestServlet;

import java.util.logging.Logger;


/**
 * Created by sgrimanov on 07.10.2016.
 */

public class Main {


    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(Main.class.getName());
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        MirrorRequestServlet mirrorRequestServlet = new MirrorRequestServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/");

        ServletContextHandler context1 = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorRequestServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context1);
        server.start();
        logger.info("Server started");
        server.join();
    }
}
