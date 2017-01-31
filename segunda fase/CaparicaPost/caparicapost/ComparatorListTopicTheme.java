package caparicapost;

import comando.Comando;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class ComparatorListTopicTheme implements Comparator<Article> {

    @Override
    public int compare(Article a1, Article a2) {
        String date1;
        String date2;
        if (a1.GetType().equalsIgnoreCase(Comando.CHRONICLE)) {
            if (((Chronicle) a1).IsPublished()) {
                date1 = ((Chronicle) a1).GetSimplePublishDate();
            } else date1 = a1.GetDate();
        } else {
            date1 = a1.GetDate();
        }
        if (a2.GetType().equalsIgnoreCase(Comando.CHRONICLE)) {
            if (((Chronicle) a2).IsPublished()) {
                date2 = ((Chronicle) a2).GetSimplePublishDate();
            } else date2 = a2.GetDate();
        } else {
            date2 = a2.GetDate();
        }
        GregorianCalendar cal1 = GetCalendar(date1);
        GregorianCalendar cal2 = GetCalendar(date2);
        if (GetYear(cal1) == GetYear(cal2)) {
            if (GetMonth(cal1) == GetMonth(cal2)) {
                return GetDayOfMonth(cal2) - GetDayOfMonth(cal1);
            } else return GetMonth(cal2) - GetMonth(cal1);
        } else {
            return GetYear(cal2) - GetYear(cal1);
        }
    }

    public GregorianCalendar GetCalendar(String newDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(Comando.DATE);
            Date date = format.parse(newDate);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int GetYear(GregorianCalendar cal) {
        return cal.get(GregorianCalendar.YEAR);
    }

    public int GetMonth(GregorianCalendar cal) {
        return (cal.get(GregorianCalendar.MONTH) + 1);
    }

    public int GetDayOfMonth(GregorianCalendar cal) {
        return cal.get(GregorianCalendar.DAY_OF_MONTH);
    }
}
