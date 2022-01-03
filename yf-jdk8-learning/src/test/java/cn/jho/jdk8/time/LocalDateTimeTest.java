package cn.jho.jdk8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-07 22:48
 */
public class LocalDateTimeTest {

    /*
     * LocalDate
     * LocalTime
     * LocalDateTime
     */
    @Test
    public void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime localDateTime = LocalDateTime.of(2021, 11, 7, 22, 49, 10);
        System.out.println(localDateTime);

        LocalDateTime plusYears = now.plusYears(2);
        System.out.println(plusYears);

        LocalDateTime minusMonths = now.minusMonths(2);
        System.out.println(minusMonths);

        System.out.println(now.getYear());
        System.out.println(now.getMonth());
    }

    /*
     *  Instant: 时间戳
     */
    @Test
    public void testInstant() {
        // 默认获取UTC时区
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(now.toEpochMilli());

        // 设置时区偏移量为8
        OffsetDateTime offset = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offset);

        Instant ofEpochSecond = Instant.ofEpochSecond(1);
        System.out.println(ofEpochSecond);
    }

    /*
     * Duration: 计算两个时间之间的间隔
     */
    @Test
    public void testDuration() {
        Instant before = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant after = Instant.now();

        Duration duration = Duration.between(before, after);
        System.out.println(duration);
        System.out.println(duration.toMillis());
        System.out.println("--------------------------");

        LocalTime lt1 = LocalTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1, lt2).toMillis());
    }

    /*
     * Period: 计算两个日期之间的间隔
     */
    @Test
    public void testPeriod() {
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(2015, 1, 1);

        Period period = Period.between(now, of);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /*
     * TemporalAdjusters：时间校正器
     */
    @Test
    public void testTemporalAdjuster() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间：" + now);

        // 将day指定为10号
        LocalDateTime ldt = now.withDayOfMonth(10);
        System.out.println(ldt);

        // 获取下一个周五的时间
        LocalDateTime ldt1 = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(ldt1);

        // 自定义：获取下一个工作日的时间
        LocalDateTime ldt2 = now.with(l -> {
            LocalDateTime localDateTime = (LocalDateTime) l;
            DayOfWeek day = localDateTime.getDayOfWeek();
            if (DayOfWeek.FRIDAY.equals(day)) {
                return localDateTime.plusDays(3);
            } else if (DayOfWeek.SATURDAY.equals(day)) {
                return localDateTime.plusDays(2);
            } else {
                return localDateTime.plusDays(1);
            }
        });
        System.out.println(ldt2);
    }

    /*
     * DateTimeFormatter：格式化时间/日期
     */
    @Test
    public void testDateTimeFormatter() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        System.out.println(now.format(dtf));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String formatTime = dtf2.format(now);
        System.out.println(formatTime);

        TemporalAccessor parse = dtf2.parse(formatTime);
        System.out.println(parse);
    }

    /*
     * ZonedDate
     * ZonedTime
     * ZonedDateTime
     */
    @Test
    public void testZone() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(zoneIds);

        LocalDateTime aden = LocalDateTime.now(ZoneId.of("Asia/Aden"));
        System.out.println(aden);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime shangHai = ldt.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(shangHai);
    }

}
