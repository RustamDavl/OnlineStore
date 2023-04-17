package servlets;

import dto.ShowGoodsDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.GoodsService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/listOfOrders")
public class ListOfOrdersForCurrentGood extends HttpServlet {

    private final GoodsService goodsService = GoodsService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<String> listOfNames = goodsService.getGoods()
                .stream()
                .map(ShowGoodsDto::getName).toList();

        req.setAttribute("products", listOfNames);

        req.getRequestDispatcher(JspHelper.pathForName("AddExisting"))
                .forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var prodName = req.getParameter("prodName");




    }
}
