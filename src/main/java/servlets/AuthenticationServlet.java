package servlets;

import dto.ReadAdminDto;
import dto.ReadBuyerDto;
import hibDto.ReadBuyerDtoHib;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import roles.Role;
import services.AdminService;
import services.BuyerService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {

    private final BuyerService buyerService = BuyerService.getInstance();

    private final AdminService adminService = AdminService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher(JspHelper.pathForName("Authentication"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var role = req.getParameter("role");
        var email = req.getParameter("email");
        var password = req.getParameter("password");



        if(role.equals("BUYER")) {
            buyerService.login2(email, password)
                    .ifPresentOrElse(readBuyerDtoHib -> onBuyerLoginSuccess2(readBuyerDtoHib, req, resp),
                            () -> onLoginError(req, resp));

//            buyerService.login(email, password)
//                    .ifPresentOrElse(readBuyerDto -> onBuyerLoginSuccess(readBuyerDto, req, resp),
//                            () -> onLoginError(req, resp));

        }
        else if(role.equals("ADMIN")) {

            adminService.login(email,password)
                    .ifPresentOrElse(readAdminDto -> onAdminLoginSuccess(readAdminDto, req, resp),
                            () -> onLoginError(req, resp));
        }
    }

    @SneakyThrows
    private void onLoginError(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/authentication?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onBuyerLoginSuccess2(ReadBuyerDtoHib readBuyerDtoHib, HttpServletRequest req, HttpServletResponse resp){
        req.getSession().setAttribute("buyer", readBuyerDtoHib);

        resp.sendRedirect("/buyerHomepage");
    }
    @SneakyThrows
    private void onBuyerLoginSuccess(ReadBuyerDto readBuyerDto, HttpServletRequest req, HttpServletResponse resp){
        req.getSession().setAttribute("buyer", readBuyerDto);

        resp.sendRedirect("/buyerHomepage");
    }

    @SneakyThrows
    private void onAdminLoginSuccess(ReadAdminDto readAdminDto, HttpServletRequest req, HttpServletResponse resp){
        req.getSession().setAttribute("admin", readAdminDto);

        resp.sendRedirect("/adminHomepage");
    }


}
