package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "")
public class ShowPizza extends HttpServlet {

    private final PizzaService pizzaService;
    private final SpringTemplateEngine springTemplateEngine;

    public ShowPizza(PizzaService pizzaService, SpringTemplateEngine springTemplateEngine) {
        this.pizzaService = pizzaService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        List<Pizza> pizzas = this.pizzaService.listPizzas();
        webContext.setVariable("pizzas", pizzas);
        webContext.setVariable("error", req.getSession().getAttribute("error"));
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("show-pizza.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pizzaType = req.getParameter("pizza");
        if (pizzaType == null || pizzaType.isEmpty()) {
            req.getSession().setAttribute("error", "You must select a pizza");
            resp.sendRedirect("/");
            return;
        }
        req.getSession().setAttribute("pizzaType", pizzaType);
        resp.sendRedirect("/selectPizzaSize");
    }
}
