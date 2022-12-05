package FormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
    static SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd,yyyy");
    static SimpleDateFormat sdfDate1 = new SimpleDateFormat("MMM dd,yyyy");

    public static String formatDate(Date date) {
        return sdfDate.format(date);
    }

    public static Date formatStringToDate(String input) throws ParseException {
        return sdfDate1.parse(input);
    }
}
