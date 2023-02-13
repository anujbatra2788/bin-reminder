package au.com.anuj.bin.collection;

import au.com.anuj.bin.config.BinConfiguration;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class BinCollectionDate {

    private final Calendar TODAY;

    public BinCollectionDate() {
        TODAY = Calendar.getInstance();
    }

    public BinCollectionDate(final Calendar calendar) {
        TODAY = Calendar.getInstance();
        TODAY.setTime(calendar.getTime());
    }

    public Date getNextDateForBin(final BinConfiguration configuration) {
        return getNextDateForBin(configuration, TODAY.getTime());
    }

    public Date getNextDateForBin(final BinConfiguration configuration, final Date from) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(configuration.getStartDate());
        startDate.set(Calendar.HOUR, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Calendar computeFromDate = Calendar.getInstance();
        computeFromDate.setTime(from);
        if (startDate.after(computeFromDate) || DateUtils.isSameDay(startDate, computeFromDate)) {
            return startDate.getTime();
        } else {
            Boolean foundNextDate = Boolean.FALSE;
            Calendar startDateForModification = Calendar.getInstance();
            startDateForModification.setTime(startDate.getTime());

            while(!foundNextDate) {
                startDateForModification.setTime(DateUtils.addDays(startDateForModification.getTime(), configuration.getFrequency()));
                if((startDateForModification.after(computeFromDate) || DateUtils.isSameDay(startDateForModification, computeFromDate))
                        && startDateForModification.get(Calendar.DAY_OF_WEEK) == configuration.getDay()) {
                    foundNextDate = true;
                }
            }
            return startDateForModification.getTime();
        }
    }
}
