package javatry.enjoy.chooh;

import javatry.colorbox.unit.ColorBoxTestCase;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 文字列のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ikezaki
 */
public class ChoOhEx0StreamStringTest extends ColorBoxTestCase {


    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * カラーボックスに入ってる文字列の中で、一番長い文字列は？
     */
    public void test_length_findMax() {
        // TODO done oh Optional型なので変数名には"Opt"をつけよう by yuto (2017/05/27)
        Optional<String> maxLenStrOpt = getColorBoxList().stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream().map(boxSpace -> (boxSpace.getContents())))
                .filter(contents -> contents instanceof String)
                .map(String.class::cast)
                .max(Comparator.comparing(String::length));

        // TODO done oh [修行] if文を使わずに処理をしてみよう by yuto (2017/05/27)
        log(maxLenStrOpt.isPresent() ? "一番長い文字列は " + maxLenStrOpt.get() : "カラーボックスに入ってる文字列はありません");
    }

    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        int strLengthSum = getColorBoxList().stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream().map(boxSpace -> (boxSpace.getContents())))
                .filter(contents -> contents instanceof String)
                .map(String.class::cast)
                .mapToInt(colorBoxStr -> colorBoxStr.length()).sum();

        log("文字列の長さの合計は " + strLengthSum);
    }
    
    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    public void test_startsWith_findFirstWord() {
        // TODO done oh この変数名はちょっと冗長かなぁ... colorList とかでいいよ by yuto (2017/05/27)
        List<String> colorList = getColorBoxList().stream()
                .filter(colorBox -> colorBox.getSpaceList().stream()
                        .filter(boxSpace -> (boxSpace.getContents()) instanceof String)
                        .anyMatch(boxSpace -> ((String) boxSpace.getContents()).startsWith("かまくら")))
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());

        if (colorList.isEmpty()) {
            log("「かまくら」で始まる文字列はありません");
        } else {
            colorList.forEach(colorName -> log("「かまくら」で始まる文字列をしまっているカラーボックスの色は " + colorName));
        }
    }
}