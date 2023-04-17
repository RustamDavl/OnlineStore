package servlets;

import dto.ReadBuyerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.OrdersService;
import util.JspHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/myGoods")
public class MyGoodsServlet extends HttpServlet {

    private final OrdersService ordersService = OrdersService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ReadBuyerDto buyer = (ReadBuyerDto) req.getSession().getAttribute("buyer");

        List<String> list = new ArrayList<>();

        list = ordersService.showOrderDto(buyer.getId()).stream()
                        .map(showOrderDto -> """
                                number : %s; product: %s
                                """.formatted(showOrderDto.getNumber(), showOrderDto.getGoodsAndAmount()))
                                .collect(Collectors.toList());


        req.setAttribute("orders", list);




        req.getRequestDispatcher(JspHelper.pathForName("ShowMyGoods"))
                .forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
