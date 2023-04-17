package servlets;

import dto.ShowGoodsDto;
import dto.UpdateGoodsDto;
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


@WebServlet("/addExisting")
public class AddExistingProduct extends HttpServlet {

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

        UpdateGoodsDto updateGoodsDto = UpdateGoodsDto.builder()
                .amount(req.getParameter("amount"))
                .prodName(req.getParameter("productName"))
                .build();

        try {


            goodsService.updateAmount(updateGoodsDto);

            req.setAttribute("update", List.of("Updated!"));
            doGet(req, resp);

        }catch (ValidationException e) {
            req.setAttribute("errors", e.getList());
            doGet(req, resp);
        }




    }
}
