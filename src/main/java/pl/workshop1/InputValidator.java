package pl.workshop1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {


    public boolean validateTaskDate(String dateStr) {

        int currentYear = Calendar.YEAR;
        String myDate = dateStr;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        long millis = 0;
            try {
                date = simpleDateFormat.parse(myDate);
                millis = date.getTime();
            } catch (ParseException e) {
            }

        String[] dateArr = dateStr.split("-");
        try {
            if ((Integer.parseInt(dateArr[0]) >= currentYear && Integer.parseInt(dateArr[0]) < 2099) /*checks year*/
                    && (Integer.parseInt(dateArr[1]) > 0 && Integer.parseInt(dateArr[1]) <= 12)  /*checks month*/
                    && (Integer.parseInt(dateArr[2]) > 0 && Integer.parseInt(dateArr[2]) <= 31) /*checks day*/
                    && System.currentTimeMillis() < millis/*checks if given date is in the future*/) {
                return true;
            } else {
                System.out.println("You entered wrong input.");
                return false;
            }
        }catch (Exception e){
            System.out.println("You entered wrong input.");
        };
        return false;
    }

    public boolean validateImportanceInput(String importanceStr) {

        //regex pattern setup
        String dateRegex = "[tT]rue|[fF]alse";

        // check if to add or remove
        Pattern patternDate = Pattern.compile(dateRegex);
        Matcher matcherDate = patternDate.matcher(importanceStr);

        if (matcherDate.matches()) {
            return true;
        }
        return false;
    }
}
