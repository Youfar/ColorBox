package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.temporal.ChronoUnit;
import java.time.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.*;
import java.util.*;

/**
 * 日付関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ChoOh
 */
public class ChoOhQ2DateTest extends ColorBoxTestCase {

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
                    log(toLocalDate(contents).toString());
                }
            }
        }
    }

    public String getCharacterWeekOfDay (String englishCharater) {
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
    //                                                                              Basic
    //                                                                             =======
    /**
     * カラーボックスに入っている最初の日付は何曜日？
     */
    public void test_weekOfDay() {
        LocalDateTime dt = LocalDateTime.now();
        LocalDate dtx = toLocalDate(dt);
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime) {
                    if (toLocalDate(contents).isBefore(dtx)) {
                        dtx = toLocalDate(contents);
                        log("最初の日付は " + getCharacterWeekOfDay(dtx.getDayOfWeek().toString()));
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
            if (colorBox.getColor().toString() == "yellow") {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate ||contents instanceof LocalDateTime) {
                        strList.add(toLocalDate(contents).plusDays(3));
                    }
                }
            }
        }
        Iterator<LocalDate> i = strList.iterator();
        if (strList.isEmpty()) {
            log("色がyellowのカラーボックスに入っている日付はありません");
        } else {
            while(i.hasNext()) {
                log("色がyellowのカラーボックスに入っている二つの日付にそれぞれ3日足すと " + getCharacterWeekOfDay(i.next().getDayOfWeek().toString()));
            }
        }
    }

    /**
     * 来年(2018年)の新卒が入社する日は何曜日？
     */
    public void test_weekOfDayOf2017Newcomer() {
        LocalDate dt = toLocalDate("2018/04/01");
        if(dt.getDayOfWeek().toString() == "SUNDAY" || dt.getDayOfWeek().toString() == "SATURDAY") {
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
            if (colorBox.getColor().toString() == "yellow") {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate ||contents instanceof LocalDateTime) {
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
