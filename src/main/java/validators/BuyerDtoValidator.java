package validators;

import dto.CreateBuyerDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validators.additonal.ValidAddress;
import validators.additonal.ValidBirthday;

import validators.errors.ValidationResultErrors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerDtoValidator implements Validator<CreateBuyerDto>{

    private static final BuyerDtoValidator INSTANCE = new BuyerDtoValidator();

    public static BuyerDtoValidator getInstance() {
        return INSTANCE;
    }


    public  ValidationResultErrors isValid(CreateBuyerDto object) {

        var result = new ValidationResultErrors();

        if(object.getName().equals("") || !object.getName().matches("[a-zA-z]*")) {

            result.add("name folder is empty or doesnt match the correct name");
        }
        if(object.getLastName().equals("") || !object.getLastName().matches("[a-zA-z]*")) {

            result.add("surname folder is empty or doesnt match the correct surname");
        }
        if(object.getEmail().equals("") || !object.getEmail().matches("^[a-z]\\w+@(g)?mail.(com|ru)")){

            result.add("email folder is empty or doesnt match the correct email");

        }
        if(object.getPassword().equals("")) {
            result.add("please, enter the password");
        }
        if(!object.getPhone().matches("\\d{11}")){
            result.add("phone folder is null or doesnt match the correct number");
        }
        if(!ValidAddress.isValid(object.getAddress())) {

            result.add(ValidAddress.getMessageOfMistakes());
        }
        if(!ValidBirthday.isValid(object.getBirthday())) {
            result.add("birthday folder is empty or incorrect");
        }
        if(object.getRole().equals("")){
            result.add("role is empty");
        }


        return result;

    }
}
