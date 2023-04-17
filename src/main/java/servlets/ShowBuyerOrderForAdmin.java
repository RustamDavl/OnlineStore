package servlets;

import dto.ShowOrderDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.OrdersService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/showOrderForAdmin")
public class ShowBuyerOrderForAdmin extends HttpServlet {

    private final OrdersService ordersService = OrdersService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<String> list = ordersService.showOrderDto(Integer.parseInt(req.getParameter("id")))
                .stream()
                .map(showOrderDto -> String.format("Number : %s; Product name and amount : %s",
                        showOrderDto.getNumber(), showOrderDto.getGoodsAndAmount()))
                .toList();

        req.setAttribute("orders", list);
        req.getRequestDispatcher(JspHelper.pathForName("ShowBuyerOrderForAdmin"))
                .forward(req, resp);

    }
}
