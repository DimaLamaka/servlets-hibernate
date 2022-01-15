package by.lamaka.servlets.web;

import javax.servlet.*;
import javax.servlet.http.*;

public class CarServletListener implements HttpSessionListener, ServletRequestListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session created: ID = " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session destroyed: ID = " + se.getSession().getId());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destroyed. Remote IP: " + sre.getServletRequest().getRemoteAddr());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request initialized. Remote IP: " + sre.getServletRequest().getRemoteAddr());
    }
}
