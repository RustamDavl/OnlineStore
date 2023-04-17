package services;


import dao.OrdersDao;
import dto.CreateOrderDto;
import dto.ShowOrderDto;
import entities.Orders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validators.SaveGoodsValidator;
import validators.SaveOrderDtoValidator;
import validators.errors.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersService {

    private final OrdersDao ordersDao = OrdersDao.getInstance();
    private static final OrdersService INSTANCE = new OrdersService();

    private final SaveOrderDtoValidator saveOrderDtoValidator = SaveOrderDtoValidator.getInstance();


    public List<ShowOrderDto> showOrderDto(Integer id) {
        return ordersDao.findAllById(id)
                .stream()
                .map(orders -> ShowOrderDto.builder()
                        .number(orders.getNumber())
                        .goodsAndAmount(orders.getProdName())
                        .build())
                .collect(Collectors.toList());
    }

    public void saveOrder(CreateOrderDto createOrderDto) {


        var list = saveOrderDtoValidator.isValid(createOrderDto);

        if(!list.isEmpty()) {

            throw new ValidationException(list.getErrorList());
        }

        Orders order = Orders.builder()
                .buyerId(createOrderDto.getBuyerId())
                .amount(Integer.parseInt(createOrderDto.getAmount()))
                .prodName(createOrderDto.getProdName())
                .build();

        ordersDao.save(order);


    }









    public static OrdersService getInstance() {
        return INSTANCE;
    }


}
