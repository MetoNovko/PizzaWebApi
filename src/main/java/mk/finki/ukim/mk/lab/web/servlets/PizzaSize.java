package mk.finki.ukim.mk.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/selectPizzaSize")
public class PizzaSize extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public PizzaSize(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pizzaType = (String) req.getSession().getAttribute("pizzaType");
        req.getSession().setAttribute("error", "");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizza", pizzaType);
        this.springTemplateEngine.process("select-size", webContext, resp.getWriter());
    }

}
