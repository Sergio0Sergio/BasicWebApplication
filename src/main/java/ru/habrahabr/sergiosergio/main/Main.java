package ru.habrahabr.sergiosergio.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.habrahabr.sergiosergio.servlets.AllRequestsServlet;

import java.util.logging.Logger;


/**
 * Created by sgrimanov on 07.10.2016.
 */
public class Main {

    public static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        log.info("Server started");
        server.join();
    }
}
