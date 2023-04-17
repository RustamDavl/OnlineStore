package validators.additonal;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ValidBirthday {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate format(String birthday) {
        return LocalDate.parse(birthday,formatter);
    }

    public static boolean isValid(String birthday) {

        try {
            return Optional.ofNullable(birthday)
                    .map(ValidBirthday::format)
                    .isPresent();
        }catch (DateTimeException e) {
            return false;
        }

    }
}
