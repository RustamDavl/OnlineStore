package servlets;

import dto.BuyGoodsDto;
import dto.CreateOrderDto;
import dto.ReadBuyerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.GoodsService;
import services.OrdersService;
import util.JspHelper;
import validators.errors.ValidationException;

import java.io.IOException;
import java.util.List;

@WebServlet("/buyThisGood")
public class CurrentGood extends HttpServlet {


    private final GoodsService goodsService = GoodsService.getInstance();


    private final OrdersService ordersService = OrdersService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        var prodName = req.getParameter("prodName");

        req.setAttribute("description", goodsService.getDescriptionByName(prodName));
        req.setAttribute("prodName", prodName);
        req.getRequestDispatcher(JspHelper.pathForName("ShowCurrentGood"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ReadBuyerDto readBuyerDto = (ReadBuyerDto) req.getSession().getAttribute("buyer");

        var prodName = req.getParameter("prodName");
        var amount = req.getParameter("amount");


        CreateOrderDto createOrderDto = CreateOrderDto.builder()
                .buyerId(readBuyerDto.getId())
                .prodName(prodName)
                .amount(amount)
                .build();

        try {

            ordersService.saveOrder(createOrderDto);
            req.setAttribute("successfully", List.of("successfully!"));
            doGet(req, resp);
        } catch (ValidationException e) {

            req.setAttribute("errors", e.getList());
            doGet(req, resp);


        }


    }
}
