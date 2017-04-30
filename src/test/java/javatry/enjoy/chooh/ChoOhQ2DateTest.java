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
                //LocalDateTime dt = LocalDateTime.now();
                if (contents instanceof LocalDateTime) {
                    if (toLocalDate(contents).isBefore(dtx)) {
                        dtx = toLocalDate(contents);
                        log("最初の日付は何曜日 " + dtx.getDayOfWeek());
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
                log("色がyellowのカラーボックスに入っている二つの日付にそれぞれ3日足すと " + i.next().getDayOfWeek() + " 曜日");
            }
        }
    }

    /**
     * 来年(2018年)の新卒が入社する日は何曜日？
     */
    public void test_weekOfDayOf2017Newcomer() {
        LocalDate dt = toLocalDate("2018/04/01");
        log(dt.getDayOfWeek());
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
            Period diff = Period.between(strList.get(0), strList.get(1));
            long diffInDays = ChronoUnit.DAYS.between(strList.get(1), strList.get(0));
            //long elapsedDays = ChronoUnit.DAYS.between(strList.get(0), strList.get(1));
            log(diffInDays);
        }
    }
}
