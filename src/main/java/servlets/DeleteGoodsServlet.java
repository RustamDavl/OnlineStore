package servlets;

import dto.DeleteGoodsDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.GoodsService;
import util.JspHelper;

import validators.errors.ValidationException;

import java.io.IOException;
import java.util.List;

@WebServlet("/deleteGoods")
public class DeleteGoodsServlet extends HttpServlet {

    private final GoodsService goodsService = GoodsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JspHelper.pathForName("deleteGoods"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DeleteGoodsDto deleteGoodsDto = DeleteGoodsDto.builder()
                .productName(req.getParameter("productName"))
                .build();

        try {


            goodsService.deleteGoods(deleteGoodsDto);
            req.setAttribute("deleted", List.of("Deleted!"));
            doGet(req, resp);
        }catch (ValidationException e) {
            req.setAttribute("errors", e.getList());
            doGet(req, resp);
        }


    }
}
