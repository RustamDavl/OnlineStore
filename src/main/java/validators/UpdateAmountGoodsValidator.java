package validators;

import dto.UpdateGoodsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import validators.errors.ValidationResultErrors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateAmountGoodsValidator implements Validator<UpdateGoodsDto>{

    private static final UpdateAmountGoodsValidator INSTANCE = new UpdateAmountGoodsValidator();

    public static UpdateAmountGoodsValidator getInstance() {
        return INSTANCE;
    }

    public ValidationResultErrors isValid(UpdateGoodsDto object) {
        var list = new ValidationResultErrors();
        if(object.getAmount().equals("") || !object.getAmount().matches("\\d+")){
            list.add("amount field is empty or incorrect");
        }

        if(object.getProdName().equals("")) {
            list.add("name field is empty");
        }

        return list;
    }
}
