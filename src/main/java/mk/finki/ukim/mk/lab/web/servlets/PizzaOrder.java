package mk.finki.ukim.mk.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/pizzaOrder")
public class PizzaOrder extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String size = req.getParameter("size");
        String pizzaType = (String) req.getSession().getAttribute("pizzaType");
        req.getSession().setAttribute("error", "");
        if (pizzaType.equals("Margherita")) {
            req.getSession().setAttribute("error", "You can't select this pizza type!");
            resp.sendRedirect("/");
            return;
        }
        req.getSession().setAttribute("pizzaSize", size);
        resp.sendRedirect("/pizzaOrder");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        String pizzaType = (String) req.getSession().getAttribute("pizzaType");
        String pizzaSize = (String) req.getSession().getAttribute("pizzaSize");

        webContext.setVariable("pizzaSize", pizzaSize);
        webContext.setVariable("pizzaType", pizzaType);

        this.springTemplateEngine.process("delivery-info.html", webContext, resp.getWriter());
    }
}
