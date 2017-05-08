package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * 日付関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 *
 * @author ChoOh
 */
public class ChoOhQ2DateTest extends ColorBoxTestCase {

    /**
     * 英語の曜日から日本語の曜日を変わるメソッド
     */
    public String getCharacterWeekOfDay(String englishCharater) {
        Map<String, String> charaterDict = new HashMap<>();
        charaterDict.put("MONDAY", "月曜日");
        charaterDict.put("TUESDAY", "火曜日");
        charaterDict.put("WEDNESDAY", "水曜日");
        charaterDict.put("THURSDAY", "木曜日");
        charaterDict.put("FRIDAY", "金曜日");
        charaterDict.put("SATURDAY", "土曜日");
        charaterDict.put("SUNDAY", "日曜日");
        return (charaterDict.get(englishCharater));
    }

    // ===================================================================================
    //                                                                             Convert
    //                                                                             =======

    /**
     * カラーボックスに入っている日付をスラッシュ区切りのフォーマットで表示したら？
     */
    public void test_convert() {
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime) {
                    log(toLocalDateTime(contents).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")).toString());
                } else if (contents instanceof LocalDate) {
                    log(toLocalDateTime(contents).format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString());
                }
            }
        }
    }

    // ===================================================================================
    //                                                                              Basic
    //                                                                             =======

    /**
     * カラーボックスに入っている最初の日付は何曜日？
     */
    public void test_weekOfDay() {
        LocalDate minDate = LocalDate.MAX;
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime) {
                    if (toLocalDate(contents).isBefore(minDate)) {
                        minDate = toLocalDate(contents);
                        log("カラーボックスに入っている最初の日付は " + getCharacterWeekOfDay(minDate.getDayOfWeek().toString()));
                    }
                }
            }
        }
    }

    /**
     * 色がyellowのカラーボックスに入っている二つの日付にそれぞれ3日足すと、それぞれ何曜日になる？
     */
    public void test_addThreeDays() {
        List<LocalDate> strList = new ArrayList<>();
        for (ColorBox colorBox : getColorBoxList()) {
            if (colorBox.getColor().toString().equals("yellow")) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate || contents instanceof LocalDateTime) {
                        strList.add(toLocalDate(contents).plusDays(3));
                    }
                }
            }
        }
        Iterator<LocalDate> i = strList.iterator();
        if (strList.isEmpty()) {
            log("色がyellowのカラーボックスに入っている日付はありません");
        } else {
            while (i.hasNext()) {
                log("色がyellowのカラーボックスに入っている二つの日付にそれぞれ3日足すと " + getCharacterWeekOfDay(i.next().getDayOfWeek().toString()) + "になる");
            }
        }
    }

    /**
     * 来年(2018年)の新卒が入社する日は何曜日？
     */
    public void test_weekOfDayOf2017Newcomer() {
        LocalDate dt = toLocalDate("2018/04/01");
        if (dt.getDayOfWeek().toString().equals("SUNDAY") || dt.getDayOfWeek().toString().equals("SATURDAY")) {
            log("来年(2018年)の新卒が入社する日は月曜日");
        } else {
            log("来年(2018年)の新卒が入社する日は" + getCharacterWeekOfDay(dt.getDayOfWeek().toString()));
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========

    /**
     * 色がyellowのカラーボックスに入っている二つの日付の日数の差は？
     */
    public void test_diffDay() {
        List<LocalDate> strList = new ArrayList<>();
        for (ColorBox colorBox : getColorBoxList()) {
            if (colorBox.getColor().toString().equals("yellow")) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate || contents instanceof LocalDateTime) {
                        strList.add(toLocalDate(contents));
                    }
                }
            }
        }
        if (strList.isEmpty()) {
            log("色がyellowのカラーボックスに入っている日付はありません");
        } else {
            long diffInDays = ChronoUnit.DAYS.between(strList.get(1), strList.get(0));
            log(diffInDays);
        }
    }
}
