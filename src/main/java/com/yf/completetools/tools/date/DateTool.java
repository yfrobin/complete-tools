package com.yf.completetools.tools.date;

import com.yf.completetools.constants.WeekEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yefei
 * @Date: create in 2019-07-05
 * @Desc:
 */
@Api("日期操作类API")
public class DateTool {

    @ApiOperation("判断一个日期是不是周末")
    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.SUNDAY || day == Calendar.SATURDAY;
    }

    @ApiOperation("判断一个日期是不是工作日")
    public static boolean isWorkDay(Date date) {
        return !isWeekend(date);
    }

    @ApiOperation("判断一个日期是周几")
    public static String getWeekDay(Date date) {
        String reStr = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                reStr = WeekEnum.Sunday.getMsg();
                break;
            case 2:
                reStr = WeekEnum.Monday.getMsg();
                break;
            case 3:
                reStr = WeekEnum.Tuesday.getMsg();
                break;
            case 4:
                reStr = WeekEnum.Wednesday.getMsg();
                break;
            case 5:
                reStr = WeekEnum.Thursday.getMsg();
                break;
            case 6:
                reStr = WeekEnum.Friday.getMsg();
                break;
            case 7:
                reStr = WeekEnum.Saturday.getMsg();
                break;
            default:
                break;
        }
        return reStr;
    }

    @ApiOperation("将Date转换为LocalDate")
    public static LocalDate parseLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    @ApiOperation("将LocalDate转换为Date")
    public static Date parseDate(LocalDate date) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    @ApiOperation("获取一个时间段的所有日期")
    public List<Date> getDateList(Date startDate, Date endDate) {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate startLocalDate = parseLocalDate(startDate);
        LocalDate endLocalDate = parseLocalDate(endDate);
        while (startLocalDate.isBefore(endLocalDate) || startLocalDate.isEqual(endLocalDate)) {
            dateList.add(startLocalDate);
            startLocalDate = startLocalDate.plusDays(1);
        }
        return dateList.stream().map(i -> parseDate(i)).collect(Collectors.toList());
    }


    @Test
    public void test() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(isWeekend(sdf.parse("2017-05-19")));
        System.out.println(getWeekDay(sdf.parse("2017-05-19")));

    }


}
