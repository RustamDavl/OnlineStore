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
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/goods")
public class ShowGoodsServlet extends HttpServlet {


    @Serial
    private static final long serialVersionUID = 1L;
    private static Integer limit = 3;
    private static final GoodsService goodsService = GoodsService.getInstance();



    //private static final Integer amountOfRows = goodsService.getAmountOfRows();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //Integer limit = 3;
        if (req.getAttribute("limit") != null) {

            limit += (Integer) req.getAttribute("limit");


        }
        req.setAttribute("goods", goodsService.getGoodsWithFilter(limit));



        req.getRequestDispatcher(JspHelper.pathForName("showGoods"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        var param = req.getParameter("offsetLimit");
        int num = Integer.parseInt(param);

        req.setAttribute("limit", num);


        doGet(req, resp);
    }
}
