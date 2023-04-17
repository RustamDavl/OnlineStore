package validators;

import dto.SaveGoodsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validators.errors.ValidationResultErrors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveGoodsValidator implements Validator<SaveGoodsDto> {

    private static final SaveGoodsValidator INSTANCE = new SaveGoodsValidator();

     public static SaveGoodsValidator getInstance() {
        return INSTANCE;
    }
    public  ValidationResultErrors isValid(SaveGoodsDto saveGoodsDto) {

         var result = new ValidationResultErrors();
        if(saveGoodsDto.getProductName().equals("")){

            result.add("product name folder is empty or is not correct");

        }
        if(saveGoodsDto.getDescription().equals("")) {
            result.add("description  folder is empty or is not correct");
        }

        if(saveGoodsDto.getPrice().equals("") || !saveGoodsDto.getPrice().matches("\\d*")) {
            result.add("price folder is empty or is not correct");
        }
        if(saveGoodsDto.getQuantity().equals("") || !saveGoodsDto.getPrice().matches("\\d*")) {
            result.add("quantity folder is empty or is not correct");
        }
        if(saveGoodsDto.getImageName().getSubmittedFileName().equals("")) {
            result.add("image folder is empty or is not correct");
        }

        return result;

    }


}
