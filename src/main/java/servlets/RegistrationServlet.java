package servlets;

import dto.CreateBuyerDto;
import entities.Buyer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import roles.Role;
import services.BuyerService;
import util.JspHelper;
import validators.errors.ValidationException;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final BuyerService buyerService = BuyerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("roles", Role.values());

        req.getRequestDispatcher(JspHelper.pathForName("registrationPage"))
                .forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var role = req.getParameter("role");


        CreateBuyerDto createBuyerDto = CreateBuyerDto.builder()
                .name(req.getParameter("name"))
                .lastName(req.getParameter("lastName"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .phone(req.getParameter("phone"))
                .address("%s;%s;%s;%s".formatted(req.getParameter("city"),
                        req.getParameter("street"), req.getParameter("houseNum"),
                        req.getParameter("flatNum")))
                .birthday(req.getParameter("birthday"))
                .role(role)
                .build();

        try {
            buyerService.convertToBuyer(createBuyerDto);
            if (role.equals("BUYER"))
                resp.sendRedirect("/buyerHomepage");
            else if (role.equals("ADMIN")) {
                resp.sendRedirect("/adminHomepage");
            }
        } catch (ValidationException e) {

            req.setAttribute("errors", e.getList());
            doGet(req, resp);

        }


    }
}
