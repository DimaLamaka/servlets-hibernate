package by.lamaka.hibernate.web;

import by.lamaka.hibernate.config.HibernateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet(value = "/start",loadOnStartup = 1)
public class StartUpServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config){
        HibernateUtil.saveData();
    }
}
