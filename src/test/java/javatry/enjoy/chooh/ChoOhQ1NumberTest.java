package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.size.BoxSize;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 数値関連のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ChoOh
 */
public class ChoOhQ1NumberTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                             Convert
    //
    //
    //                                                                            =======

    /**
     * カラーボックスに入ってる日付の月を全て足したら？
     */
    public void test_sumMonth() {
        int monthSum = 0;
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime || contents instanceof LocalDate){
                    monthSum = monthSum + toLocalDate(contents).getMonthValue();
                }
            }
        }
        log("カラーボックスに入ってる日付の月を全て足したら " + monthSum + " ヶ月です");
    }

    /**
     * カラーボックの中に入っている、0~100までの数値の数は？
     */
    public void test_countZeroToHundred() throws Exception {
        //全部の要素をNumberに変換する
        List<Number> numberList = new ArrayList<>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                // done oh これだと0~100以外も含まれてしまわない？ by hakiba (2017/05/08)
                if (contents instanceof Number) {
                    Number numContents = (Number) contents;
                    numberList.add(numContents);
                } else if (contents instanceof Map<?, ?>) {
                    Map<?, ?> map = (Map<?, ?>) contents;
                    for (Map.Entry entry : map.entrySet()) {
                        if(entry.getKey() instanceof Number) {
                            Number numContents = ((Number) entry.getKey());
                            numberList.add(numContents);
                        } else if (entry.getValue() instanceof Number) {
                            Number numContents = ((Number) entry.getValue());
                            numberList.add(numContents);
                        }
                    }
                } else if (contents instanceof List) {
                    List<Object> ls = new ArrayList<>();
                    ls.addAll((List) contents);
                    // done oh 拡張for文で書き換えられるよ！ by hakiba (2017/05/08)
                    for (Object listObject : ls) {
                        if (listObject instanceof Number) {
                            Number numContents = (Number) listObject;
                            numberList.add(numContents);
                        }
                    }
                }
            }
        }
        // done oh なんかログに変な文字が入り込んでる... by hakiba (2017/05/08)
        // done TODO oh これSetに格納しているってことは, 同じ数字が2つ以上とか入っていた場合は, 1つとしてカウントされてしまわないかな by hakiba (2017/05/12)
        log("カラーボックの中に入っている、0~100までの数値の数は " + calcNumBetZeroAndHundred(numberList) + " です");
    }

    private int calcNumBetZeroAndHundred(List numList) {
        int countNum = 0;
        for (Object numObj: numList) {
            Number num = (Number) numObj;
            if (num.doubleValue() >= 0 && num.doubleValue() <= 100) {
                countNum++;
            }
        }
        return countNum;
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * 青色のカラーボックスに入ってる Map の中の商品で一番高いものは？
     */
    public void test_findMax() {
        int maxValue = 0;
        // done oh mapはfor文の外で使っているかな？最低限のスコープになるように宣言位置を変更しましょう by hakiba (2017/05/08)
        String maxValueItem = "";
        for (ColorBox colorBox : getColorBoxList()) {
            // done TODO oh toString()を使わずにカラーボックスの色の名前を取得しましょう by hakiba (2017/05/08)
            if (colorBox.getColor().getColorName().equals("blue")) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    Object contents = boxSpace.getContents();
                    if (contents instanceof Map<? ,?>) {
                        // done TODO oh これだったらMapへのキャストは一行でかけるね by hakiba (2017/05/12)
                        Map<?, ?> map = (Map)contents;
                        for (Map.Entry entry : map.entrySet()){
                            int priceValue = (Integer) entry.getValue();
                            if (priceValue > maxValue) {
                                maxValue = priceValue;
                            }
                        }
                        for (Map.Entry entry : map.entrySet()){
                            if (Objects.equals(maxValue, entry.getValue())) {
                                maxValueItem = (String) entry.getKey();
                            }
                        }
                    }
                }
            }
        }
        log("青色のカラーボックスに入ってる Map の中の商品で一番高いものは " + maxValueItem);
    }

    /**
     * カラーボックスの中で、一番幅が大きいものでInteger型を持っているカラーボックスの色は？
     */
    public void test_findColorBigWidthHasInteger() {
        int maxWidth = 0;
        String maxWidthColorBoxName = "";
        List<ColorBox> colorBoxList = getColorBoxList();
        // done oh カラーボックスが存在しないことを示すのはよいけど, elseの中に本処理が入ってネストが深くなっているのが嫌です。 by hakiba (2017/05/08)
        if (colorBoxList.size() == 0) {
            log("カラーボックスがそもそも存在しない");
            return;
        }
        // done TODO oh このロジック一つ大事なチェックが抜けてる。もう一度問題要件を見直してください。 by hakiba (2017/05/12)
        for (ColorBox colorBox : getColorBoxList()) {
            // done TODO oh toString()を使わずにカラーボックスの色の名前を取得しましょう by hakiba (2017/05/08)
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Integer) {
                    BoxSize size = colorBox.getSize();
                    if (size.getWidth() > maxWidth) {
                        maxWidth = size.getWidth();
                        maxWidthColorBoxName = colorBox.getColor().getColorName();
                    }
                } else if (contents instanceof Map<?, ?>) {
                    Map<?, ?> map = (Map<?, ?>) contents;
                    for (Map.Entry entry : map.entrySet()) {
                        if(entry.getKey() instanceof Integer) {
                            BoxSize size = colorBox.getSize();
                            if (size.getWidth() > maxWidth) {
                                maxWidth = size.getWidth();
                                maxWidthColorBoxName = colorBox.getColor().getColorName();
                            }
                        } else if (entry.getValue() instanceof Integer) {
                            BoxSize size = colorBox.getSize();
                            if (size.getWidth() > maxWidth) {
                                maxWidth = size.getWidth();
                                maxWidthColorBoxName = colorBox.getColor().getColorName();
                            }
                        }
                    }
                } else if (contents instanceof List) {
                    List<Object> ls = new ArrayList<>();
                    ls.addAll((List) contents);
                    for (Object listObject : ls) {
                        if (listObject instanceof Integer) {
                            BoxSize size = colorBox.getSize();
                            if (size.getWidth() > maxWidth) {
                                maxWidth = size.getWidth();
                                maxWidthColorBoxName = colorBox.getColor().getColorName();
                            }
                        }
                    }
                }
            }
        }
        log("カラーボックスの中で、一番幅が大きいものでInteger型を持っているカラーボックスの色は" + maxWidthColorBoxName);
    }

    /**
     * カラーボックスの中に入ってる BigDecimal を全て足し合わせると？
     */
    public void test_sumBigDecimal() {
        BigDecimal bigDecimalSum = BigDecimal.ZERO;
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof List) {
                    List<Object> bigDecimalList = new ArrayList<>();
                    bigDecimalList.addAll((List) contents);
                    // done oh 拡張for文で書き換えられるよ！ by hakiba (2017/05/08)
                    for (Object bigDecimalObject : bigDecimalList) {
                        if (bigDecimalObject instanceof BigDecimal) {
                            bigDecimalSum = bigDecimalSum.add((BigDecimal) bigDecimalObject);
                        }
                    }
                }
            }
        }
        log("カラーボックスの中に入ってる BigDecimal を全て足し合わせると " + bigDecimalSum + " です");
    }
}
