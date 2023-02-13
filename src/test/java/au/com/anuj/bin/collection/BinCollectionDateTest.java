package au.com.anuj.bin.collection;

import au.com.anuj.bin.BaseTest;
import au.com.anuj.bin.config.BinConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BinCollectionDateTest extends BaseTest {


    private BinCollectionDate binCollectionDate = new BinCollectionDate();

    @Test
    public void shouldReturnValidNextBinDateWhenStartDateIsInFuture() throws ParseException {
        BinConfiguration redBinConfiguration = Mockito.mock(BinConfiguration.class);

        // Set Start Date for a future date
        when(redBinConfiguration.getStartDate()).thenReturn(format.parse("2023-02-14"));

        // Set Current Date for a date before start date
        Date nextBinDate = binCollectionDate.getNextDateForBin(redBinConfiguration, format.parse("2023-02-13"));
        assertEquals("2023-02-14", format.format(nextBinDate));
    }

    @Test
    public void shouldReturnValidNextBinDateWhenStartDateIsEqualToCurrentDate() throws ParseException {
        BinConfiguration redBinConfiguration = Mockito.mock(BinConfiguration.class);

        // Set Start Date for a current date
        when(redBinConfiguration.getStartDate()).thenReturn(format.parse("2023-02-14"));

        // Set Current Date for a date equal start date
        Date nextBinDate = binCollectionDate.getNextDateForBin(redBinConfiguration, format.parse("2023-02-14"));
        assertEquals("2023-02-14", format.format(nextBinDate));
    }

    @Test
    public void shouldReturnValidNextBinDateWhenStartDateIsBeforeCurrentDateMatchingDayOfWeek() throws ParseException {
        BinConfiguration redBinConfiguration = Mockito.mock(BinConfiguration.class);

        // Set Start Date for a current date
        when(redBinConfiguration.getStartDate()).thenReturn(format.parse("2023-02-14"));
        when(redBinConfiguration.getDay()).thenReturn(3);
        when(redBinConfiguration.getFrequency()).thenReturn(7);

        // Set Current Date for a date after start date
        Date nextBinDate = binCollectionDate.getNextDateForBin(redBinConfiguration, format.parse("2023-02-28"));
        assertEquals("2023-02-28", format.format(nextBinDate));
    }

    @Test
    public void shouldReturnValidNextBinDateWhenStartDateIsBeforeCurrentDateNotMatchingDayOfWeek() throws ParseException {
        BinConfiguration redBinConfiguration = Mockito.mock(BinConfiguration.class);

        // Set Start Date for a current date
        when(redBinConfiguration.getStartDate()).thenReturn(format.parse("2023-02-14"));
        when(redBinConfiguration.getDay()).thenReturn(3);
        when(redBinConfiguration.getFrequency()).thenReturn(7);

        // Set Current Date for a date after start date
        Date nextBinDate = binCollectionDate.getNextDateForBin(redBinConfiguration, format.parse("2023-03-01"));
        assertEquals("2023-03-07", format.format(nextBinDate));
    }

    @Test
    public void shouldReturnValidNextBinDateWhenStartDateIsBeforeCurrentDateNotMatchingDayOfWeekVeryFarDate() throws ParseException {
        BinConfiguration redBinConfiguration = Mockito.mock(BinConfiguration.class);

        // Set Start Date for a current date
        when(redBinConfiguration.getStartDate()).thenReturn(format.parse("2023-02-14"));
        when(redBinConfiguration.getDay()).thenReturn(3);
        when(redBinConfiguration.getFrequency()).thenReturn(7);

        // Set Current Date for a date after start date
        Date nextBinDate = binCollectionDate.getNextDateForBin(redBinConfiguration, format.parse("2040-03-01"));
        assertEquals("2040-03-06", format.format(nextBinDate));
    }


    @Test
    public void shouldReturnValidNextBinDateWithCurrentDateHavingDayBeforeCollectionDay() throws ParseException, NoSuchFieldException, IllegalAccessException {
        BinConfiguration redBinConfiguration = Mockito.mock(BinConfiguration.class);

        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.HOUR, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);

        int currentDayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);

        if(currentDayOfWeek > 3) {
            startDate.add(Calendar.DAY_OF_MONTH, (currentDayOfWeek - 3 - 1));
            currentDayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
        }
        BinCollectionDate collectionDate = new BinCollectionDate(startDate);

        // Set Start Date for a current date
        Calendar startDateForConfig = Calendar.getInstance();
        startDateForConfig.set(Calendar.DAY_OF_MONTH, 7);
        startDateForConfig.set(Calendar.MONTH, Calendar.FEBRUARY);
        startDateForConfig.set(Calendar.YEAR, 2023);

        when(redBinConfiguration.getStartDate()).thenReturn(startDateForConfig.getTime());
        when(redBinConfiguration.getDay()).thenReturn(3);
        when(redBinConfiguration.getFrequency()).thenReturn(7);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.setTime(startDate.getTime());
        expectedDate.set(Calendar.HOUR, 0);
        expectedDate.set(Calendar.MINUTE, 0);
        expectedDate.set(Calendar.SECOND, 0);
        expectedDate.set(Calendar.MILLISECOND, 0);

        if(currentDayOfWeek < 3) {
            expectedDate.add(Calendar.DAY_OF_MONTH, 3-currentDayOfWeek);
        } else {
            expectedDate.add(Calendar.DAY_OF_MONTH, ((7-currentDayOfWeek) + 3));
        }
        // Set Current Date for a date after start date
        Date nextBinDate = collectionDate.getNextDateForBin(redBinConfiguration);
        assertEquals(format.format(expectedDate.getTime()), format.format(nextBinDate));
    }

    @Test
    public void shouldReturnValidNextBinDateWithCurrentDateHavingDayAfterCollectionDay() throws ParseException {
        BinConfiguration redBinConfiguration = Mockito.mock(BinConfiguration.class);

        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.HOUR, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        int currentDayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);

        if(currentDayOfWeek < 3) {
            startDate.add(Calendar.DAY_OF_MONTH, (currentDayOfWeek + 3));
            currentDayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
        }
        BinCollectionDate collectionDate = new BinCollectionDate(startDate);
        // Set Start Date for a current date

        Calendar startDateForConfig = Calendar.getInstance();
        startDateForConfig.set(Calendar.DAY_OF_MONTH, 7);
        startDateForConfig.set(Calendar.MONTH, Calendar.FEBRUARY);
        startDateForConfig.set(Calendar.YEAR, 2023);

        when(redBinConfiguration.getStartDate()).thenReturn(startDateForConfig.getTime());
        when(redBinConfiguration.getDay()).thenReturn(3);
        when(redBinConfiguration.getFrequency()).thenReturn(7);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.setTime(startDate.getTime());
        expectedDate.set(Calendar.HOUR, 0);
        expectedDate.set(Calendar.MINUTE, 0);
        expectedDate.set(Calendar.SECOND, 0);
        expectedDate.set(Calendar.MILLISECOND, 0);

        if(currentDayOfWeek < 3) {
            expectedDate.add(Calendar.DAY_OF_MONTH, 3-currentDayOfWeek);
        } else {
            expectedDate.add(Calendar.DAY_OF_MONTH, ((7-currentDayOfWeek) + 3));
        }
        // Set Current Date for a date after start date
        Date nextBinDate = collectionDate.getNextDateForBin(redBinConfiguration);
        assertEquals(format.format(expectedDate.getTime()), format.format(nextBinDate));
    }
}
