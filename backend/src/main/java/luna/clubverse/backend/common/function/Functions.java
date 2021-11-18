package luna.clubverse.backend.common.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions {

    public static Date stringToDate(String strDate) throws ParseException {
        final SimpleDateFormat format =  new SimpleDateFormat("dd.MM.yyyy HH:mm");
         return format.parse(strDate);

    }

    public static String dateToString(Date date) throws ParseException {
        final SimpleDateFormat format =  new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return format.format(date);

    }

}
