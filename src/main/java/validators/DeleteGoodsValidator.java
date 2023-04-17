package validators;

import dto.DeleteGoodsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validators.errors.ValidationResultErrors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteGoodsValidator implements Validator<DeleteGoodsDto> {


    private static final DeleteGoodsValidator INSTANCE = new DeleteGoodsValidator();

    public static DeleteGoodsValidator getInstance() {
        return INSTANCE;
    }
    public  ValidationResultErrors isValid(DeleteGoodsDto deleteGoodsDto) {

        var list = new ValidationResultErrors();
        if(deleteGoodsDto.getProductName().equals("")){

            list.add("product name folder is empty or incorrect");
        }

        return list;
    }


}
