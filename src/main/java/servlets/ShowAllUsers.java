package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.BuyerService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/allUsers")
public class ShowAllUsers extends HttpServlet {

    private final BuyerService buyerService = BuyerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> showInfo = buyerService.showBuyerDtoList().stream()
                .map(showBuyerDto -> String.format("Name : %s %s; Email : %s; Phone : %s",
                        showBuyerDto.getName(), showBuyerDto.getLastName() ,

                        showBuyerDto.getEmail() , showBuyerDto.getPhone())).toList();

        req.setAttribute("id", buyerService.showBuyerDtoList());
        req.setAttribute("listOfUsers", showInfo);
        req.getRequestDispatcher(JspHelper.pathForName("ShowAllUsers"))
                .forward(req, resp);

    }
}
