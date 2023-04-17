package servlets;

import dto.SaveGoodsDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.GoodsService;
import util.JspHelper;
import validators.errors.ValidationException;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1012 * 1024)
@WebServlet("/addGoods")
public class AddGoodsServlet extends HttpServlet {


    private final GoodsService goodsService = GoodsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JspHelper.pathForName("addGoods"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        SaveGoodsDto saveGoodsDto = SaveGoodsDto.builder()
                .productName(req.getParameter("productName"))
                .description(req.getParameter("productDescription"))
                .price(req.getParameter("productPrice"))
                .quantity(req.getParameter("productQuantity"))
                .imageName(req.getPart("productImage"))
                .build();


        try {



            goodsService.convertToGoodsEntity(saveGoodsDto);


            req.setAttribute("saved", List.of("Saved!"));
            doGet(req, resp);

        } catch (ValidationException e) {

            req.setAttribute("errors", e.getList());
            doGet(req, resp);
        }


    }
}
