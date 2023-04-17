package validators;


import dao.GoodsDao;
import dto.BuyGoodsDto;
import dto.CreateOrderDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validators.errors.ValidationResultErrors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveOrderDtoValidator implements Validator<CreateOrderDto> {

    private final GoodsDao goodsDao = GoodsDao.getInstance();


    private static final SaveOrderDtoValidator INSTANCE = new SaveOrderDtoValidator();

    public static SaveOrderDtoValidator getInstance() {
        return INSTANCE;
    }

    public ValidationResultErrors isValid(CreateOrderDto createOrderDto) {


        var list = new ValidationResultErrors();

        if (createOrderDto.getProdName().equals("")) {
            list.add("name folder is empty");
        }


        if (createOrderDto.getAmount().equals("") || !createOrderDto.getAmount().matches("\\d+")
                || Integer.parseInt(createOrderDto.getAmount()) <= 0) {


            list.add("amount folder is empty of incorrect");

        }

        if(list.isEmpty()) {
            int quantity = goodsDao.getQuantity(createOrderDto.getProdName());
            if(Integer.parseInt(createOrderDto.getAmount()) > quantity) {
                list.add("your amount of products exceeds the existing!");
            }
        }


        return list;

    }
}
