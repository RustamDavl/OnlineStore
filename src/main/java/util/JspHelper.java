package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JspHelper {
    private static final String BASIC_PATH = "/WEB-INF/%s.jsp";

    public static String pathForName(String jspName) {
        return String.format(BASIC_PATH, jspName);
    }


}
