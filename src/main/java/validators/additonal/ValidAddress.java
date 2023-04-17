package validators.additonal;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

//city
//street
//house num
//flat num
public class ValidAddress {


    private static final List<String> addressList = new ArrayList<>();


    private static final Map<String, String> addressMap = new LinkedHashMap<>();

    public static boolean isValid(String addressFolder) {
        String[] address = addressFolder.split(";");

        addressMap.put("city folder", "is empty");
        addressMap.put("street folder", "is empty");
        addressMap.put("house number folder", "is empty");
        addressMap.put("flat number", "is empty");

        if (address.length == 0)
            return false;

        int i = 0;

        for (Map.Entry<String, String> map : addressMap.entrySet()) {

            if (i < address.length && !address[i].equals("")) {
                map.setValue(address[i]);
            }
            i++;
        }

        return !addressMap.containsValue("is empty");
    }

    public static String getMessageOfMistakes() {
        return addressMap.entrySet().stream()
                .filter(s -> s.getValue().equals("is empty"))
                .map(s -> s.getKey() + " " + s.getValue())
                .collect(Collectors.joining(";"));
    }
}
