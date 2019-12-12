package mk.finki.ukim.mk.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirmationInfo")
public class ConfirmationInfo extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfo(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("clientAddress", clientAddress);
        resp.sendRedirect("/confirmationInfo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        String clientName = (String) req.getSession().getAttribute("clientName");
        String clientAddress = (String) req.getSession().getAttribute("clientAddress");
        String clientIp = req.getRemoteAddr();
        String clientBrowser = req.getHeader("User-Agent");
        String pizzaType = (String) req.getSession().getAttribute("pizzaType");
        String pizzaSize = (String) req.getSession().getAttribute("pizzaSize");

        webContext.setVariable("clientName", clientName);
        webContext.setVariable("clientAddress", clientAddress);
        webContext.setVariable("clientIp", clientIp);
        webContext.setVariable("clientBrowser", clientBrowser);
        webContext.setVariable("pizzaType", pizzaType);
        webContext.setVariable("pizzaSize", pizzaSize);

        this.springTemplateEngine.process("confirmation-info", webContext, resp.getWriter());
    }
}
