package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
     * 英語の曜日から日本語の曜日を変わるメソッド
     */
    // TODO oh getというよりはEnglishからJapaneseにTranslateかConvertしてる感じの名前がいいかね
    // でも多分引数をDayOfWeekに取って、getJapaneseとかの名前のほうが直感的
    // そして、自作しなくても日本語で出してくれるメソッドがあるので探してみよう！ by yuki.wakisaka (2017/05/09)
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

    /**
     * カラーボックスに入っている日付をスラッシュ区切りのフォーマットで表示したら？
     */
    public void test_convert() {
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime) {
                    // TODO oh formatの戻り値のかたを確認してみてくださいー by yuki.wakisaka (2017/05/09)
                    // [コメント] toLocalDateTime、いいメソッドを見つけたね。
                    // これ久保さんが作った便利メソッドなので、中で何してるかを覗いてみるといいと思います。 by yuki.wakisaka (2017/05/09)
                    // TODO toLocalDateTimeを使うなら、LocalDateTime, LocalDate以外も対応しやすいと思うので、共通してる親クラスでまとめてみましょう by yuki.wakisaka (2017/05/09)
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
                    // TODO oh このロジックだと、2017/5/9, 2017/4/3の順に入ってた時に両方表示されませんか？ by yuki.wakisaka (2017/05/09)
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
        // done TODO oh StringのList???って思ったら普通にLocalDateのListだった。変数名大事。 by yuki.wakisaka (2017/05/09)
        List<LocalDate> localDateList = new ArrayList<>();
        for (ColorBox colorBox : getColorBoxList()) {
            // done TODO oh "yellow".equals(...)のほうがよい
            // 理由を考えてコメントに残してみてくださいー by yuki.wakisaka (2017/05/09)
            // "yellow".equals(...)のほうを使う時、colorNameがnullの場合instanceofで判断できる、NullPointerExceptionが出さない
            // ただ、Nullを見つけて、報告しないと、これは利点ではないかな。。。 by cho.oh
//            if (colorBox.getColor().getColorName().equals("yellow")) {
            if ("yellow".equals(colorBox.getColor().getColorName())) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof LocalDate || contents instanceof LocalDateTime) {
                        localDateList.add(toLocalDate(contents).plusDays(3));
                    }
                }
            }
        }
        // TODO oh この宣言はelseの中でいいよね。変数宣言は使うスコープの中で。
        // そして今回log出すだけなら拡張for文で事足りるのでは by yuki.wakisaka (2017/05/09)
        Iterator<LocalDate> i = localDateList.iterator();
        if (localDateList.isEmpty()) {
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
        // TODO oh せっかく曜日の型があるので曜日型で比較しましょう。文字列で比較するとタイポしてもコンパイルが通って気付かないなどのリスクもあるし... by yuki.wakisaka (2017/05/09)
        // TODO oh ついでに、入社日の日付もlogに表示できるようにしてみましょうー。 + if文の中でlogを書かない形にするほうがすっきりして読みやすいですかね。 by yuki.wakisaka (2017/05/09)
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
            // TODO Listに一つしか入ってないときはどうなる？ by yuki.wakisaka (2017/05/09)
            // TODO ついでにListに三つ以上入っている時も、全ての組み合わせでlogが出せるようにしてみよう！ by yuki.wakisaka (2017/05/09)
            long diffInDays = ChronoUnit.DAYS.between(strList.get(1), strList.get(0));
            // TODO oh 急にlogが雑！w by yuki.wakisaka (2017/05/09)
            log(diffInDays);
        }
    }
}
