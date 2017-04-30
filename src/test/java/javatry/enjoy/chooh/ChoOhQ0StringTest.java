package javatry.enjoy.chooh;

import javatry.colorbox.ColorBox;
import javatry.colorbox.color.BoxColor;
import javatry.colorbox.space.BoxSpace;
import javatry.colorbox.unit.ColorBoxTestCase;

import java.time.LocalDateTime;
import java.util.*;
//import net.sf.json.JSONObject;


/**
 * 文字列のテスト。<br>
 * 何々は？と言われたら、それに該当するものをログに出力すること。
 * @author ChoOh
 */
public class ChoOhQ0StringTest extends ColorBoxTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * 最初のカラーボックスの色の名前の文字数は？
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor boxColor = colorBox.getColor();
        String colorName = boxColor.getColorName();
        log(colorName, colorName.length());
    }

    /**
     * カラーボックスで文字列の値を取り出し、リストで返すメソッド
     */
    private List<String> getColorBoxStrContentList(){
        List<String> colorBoxStrContentList = new ArrayList<>();

        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    colorBoxStrContentList.add(strContents);
                }
            }
        }
        return colorBoxStrContentList;
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長い文字列は？
     */
    public void test_length_findMax() {
        List<String> colorBoxStrContentList = getColorBoxStrContentList();
        if (colorBoxStrContentList.size() != 0) {
            colorBoxStrContentList.sort(Comparator.comparing(String::length).reversed());
            log("一番長い文字列は " + colorBoxStrContentList.get(0));
        } else {
            log("カラーボックスの中は文字列がありません");
        }
    }

    /**
     * カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？
     */
    public void test_length_findMaxMinDiff() {
        List<String> colorBoxStrContentList = getColorBoxStrContentList();
        colorBoxStrContentList.sort(Comparator.comparing(String::length).reversed());
        log("一番長いものと短いものの差は " +
                (colorBoxStrContentList.get(0).length() - colorBoxStrContentList.get(colorBoxStrContentList.size() - 1).length()) + " 文字");
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ソートして二番目を取得する、ってやり方で。
     */
    public void test_length_findSecondMax_bySort() {
        List<String> colorBoxStrContentList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    String strContents = contents.toString();
                    colorBoxStrContentList.add(strContents);
                }
            }
        }
        for (int i = 0; i < colorBoxStrContentList.size() - 1; i++) {
            for (int j = colorBoxStrContentList.size() - 1; j > i; j--) {
                if (colorBoxStrContentList.get(j-1).length() > colorBoxStrContentList.get(j).length()) {
                    String temp = colorBoxStrContentList.get(j - 1);
                    colorBoxStrContentList.set(j - 1, colorBoxStrContentList.get(j));
                    colorBoxStrContentList.set(j, temp);
                }
            }
        }
        log("二番目に長い文字列は " + colorBoxStrContentList.get(colorBoxStrContentList.size() - 2));
    }

    /**
     * カラーボックスに入ってる値 (文字列以外のものはtoString()) の中で、二番目に長い文字列は？ <br>
     * ただし、ソートして二番目を取得する、ってやり方は利用しないこと。
     */
    public void test_length_findSecondMax_nonSorted() {
        String secMaxLenStr = "";
        String maxLenStr = "";

        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents != null) {
                    String strContents = contents.toString();
                    if (strContents.length() > maxLenStr.length()) {
                        secMaxLenStr = maxLenStr;
                        maxLenStr = strContents;
                    } else if (strContents.length() > secMaxLenStr.length()){
                        secMaxLenStr = strContents;
                    }
                }
            }
        }
        log("二番目に長い文字列は " + secMaxLenStr);
    }

    /**
     * カラーボックスに入ってる文字列の長さの合計は？
     */
    public void test_length_calculateLengthSum() {
        List<String> colorBoxStrContentList = getColorBoxStrContentList();
        int listSum = colorBoxStrContentList.stream().mapToInt(w -> w.length()).sum();
        log("文字列の長さの合計は " + listSum);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * 「かまくら」で始まる文字列をしまっているカラーボックスの色は？
     */
    // TODO oh インターフェースで受ける習慣を (つまり Set だね) by yuto (2017/04/26)
    // TODO oh 順番と重複を意識しない Set を使おう！！ by yuto (2017/04/26)
    public void test_startsWith_findFirstWord() {
        List<String> colorNameList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.startsWith("かまくら")){
                        colorNameList.add(colorBox.getColor().toString());
                    }
                }
            }
        }

        // TODO oh iteratorはelseの中だけで良くない？ by yuto (2017/04/26)
        Iterator<String> i = colorNameList.iterator();
        if (colorNameList.isEmpty()) {
            log("「かまくら」で始まる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「かまくら」で始まる文字列をしまっているカラーボックスの色は " + i.next());
            }
        }
    }

    /**
     * 「いぬ」で終わる文字列をしまっているカラーボックスの色は？
     */
    public void test_endsWith_findLastWord() {
        ArrayList<String> colorNameList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.endsWith("いぬ")){
                        colorNameList.add(colorBox.getColor().toString());
                    }
                }
            }
        }
        Iterator<String> i = colorNameList.iterator();
        if (colorNameList.isEmpty()) {
            log("「いぬ」で終わる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「いぬ」で終わる文字列をしまっているカラーボックスの色は " + i.next());
            }
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * あなたのカラーボックスに入ってる「いぬ」で終わる文字列で、「いぬ」は何文字目から始まる？
     */
    public void test_indexOf_findIndex() {
        ArrayList<Integer> strPositionList = new ArrayList<Integer>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.endsWith("いぬ")){
                        strPositionList.add(strContents.indexOf("いぬ"));
                    }
                }
            }
        }
        Iterator<Integer> i = strPositionList.iterator();
        if (strPositionList.isEmpty()) {
            log("「いぬ」で終わる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「いぬ」で終わる文字列で、「いぬ」は " + i.next() + " 文字目から始まる");
            }
        }
    }

    /**
     * あなたのカラーボックスに入ってる「ず」を二つ以上含む文字列で、最後の「ず」は何文字目から始まる？
     */
    public void test_lastIndexOf_findIndex() {
        ArrayList<Integer> strPositionList = new ArrayList<Integer>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContents = contents.toString();
                    if (strContents.indexOf("ず") != strContents.lastIndexOf("ず")){
                        strPositionList.add(strContents.lastIndexOf("ず"));
                    }
                }
            }
        }
        Iterator<Integer> i = strPositionList.iterator();
        if (strPositionList.isEmpty()) {
            log("「ず」で含む文字列はありませんまた「ず」は一つだけ");
        } else {
            while(i.hasNext()) {
                log("最後の「ず」は " + i.next() + " 文字目から始まる");
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * カラーボックスに入ってる「いぬ」で終わる文字列の最初の一文字は？
     */
    public void test_substring_findFirstChar() {
        ArrayList<String> strList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContent = (String) contents;
                    if (strContent.endsWith("いぬ")){
                        strList.add(strContent.substring(0, 1));
                    }
                }
            }
        }
        Iterator<String> i = strList.iterator();
        if (strList.isEmpty()) {
            log("「いぬ」で終わる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「いぬ」で終わる文字列の最初の一文字は " + i.next());
            }
        }
    }

    /**
     * カラーボックスに入ってる「かまくら」で始まる文字列の最後の一文字は？
     */
    public void test_substring_findLastChar() {
        ArrayList<String> strList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContent = (String) contents;
                    if (strContent.startsWith("かまくら")){
                        strList.add(strContent.substring(strContent.length() - 1, strContent.length()));
                    }
                }
            }
        }
        Iterator<String> i = strList.iterator();
        if (strList.isEmpty()) {
            log("「かまくら」で始まる文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「かまくら」で始まる文字列の最後の一文字は " + i.next());
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * カラーボックスに入ってる「ー」を含んだ文字列から「ー」を全て除去したら何文字？
     */
    public void test_replace_removeBo() {
        ArrayList<Integer> strList = new ArrayList<Integer>();
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof String) {
                    String strContent = (String) contents;
                    if (strContent.contains("ー")) {
                        strList.add(strContent.replace("ー", "").length());
                    }
                }
            }
        }
        Iterator<Integer> i = strList.iterator();
        if (strList.isEmpty()) {
            log("「ー」を含んだ文字列はありません");
        } else {
            while(i.hasNext()) {
                log("「ー」を含んだ文字列から「ー」を全て除去したら " + i.next() + " 文字");
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * カラーボックスの中で、色の名前が一番長いものは？
     */
    // TODO oh カラーボックスがそもそも存在しないならエラーになるので、どうにかしよう by yuto (2017/04/26)
    public void test_findMaxColorSize() throws Exception {
        ArrayList<String> colorNameList = new ArrayList<String>();
        for (ColorBox colorBox : getColorBoxList()) {
            BoxColor color = colorBox.getColor();
            colorNameList.add(color.toString());
        }
        colorNameList.sort(Comparator.comparing(String::length).reversed());
        log("色の名前が一番長いものは " + colorNameList.get(0));
    }

    /**
     * カラーボックスの中で、2012/06/04 を示す日付が持っている秒は？
     */
    public void test_findDBFluteBirthdateSecond() throws Exception {
        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof LocalDateTime) {
                    if (toLocalDate(contents).isEqual(toLocalDate("2012/06/04"))) {
                        log("2012/06/04 を示す日付が持っている秒は " + toLocalDateTime(contents).getSecond());
                    }
                }
            }
        }
    }

    /**
     * カラーボックスの中に入っている Map を "map:{ key = value ; key = value ; ... }" という形式で表示。
     */
    public void test_showMap() throws Exception {
        String mapPrefix = "map:{ ";
        String mapSuffix = "}";
        StringBuilder mapToString = new StringBuilder();
        mapToString.append(mapPrefix);

        for (ColorBox colorBox : getColorBoxList()) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object contents = boxSpace.getContents();
                if (contents instanceof Map<?, ?>) {
                    Map<?, ?> map = (Map)contents;
                    for (Map.Entry entry : map.entrySet()){
                        mapToString.append(entry.getKey());
                        mapToString.append(" = ");
                        mapToString.append(entry.getValue());
                        mapToString.append(" ; ");
                    }
                }
            }
        }
        mapToString.delete(mapToString.lastIndexOf(";"), mapToString.length());
        mapToString.append(mapSuffix);
        log(mapToString);
    }

    /**
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = value3 }" という文字列をMapに変換してtoString()すると？
     * <pre>
     * 変換後のMapの中身は、以下のようになっていること
     *  o key1というキーに対してvalue1という値
     *  o key2というキーに対してvalue2という値
     *  o key3というキーに対してvalue3という値
     * </pre>
     */
    public void test_parseMap() throws Exception {
        String mapPrefix = "map:{";
        String mapSuffix = "}";
        String mapString = "map:{ key1 = value1 ; key2 = value2 ; key3 = value3 }";
        Map<String, String> map = new HashMap<>();

        String blankReplaceString = mapString.replace(" ","");
        String subMapString = blankReplaceString.substring(blankReplaceString.indexOf(mapPrefix) + mapPrefix.length(), blankReplaceString.lastIndexOf(mapSuffix));
        String[] keyValuePairs = subMapString.split(";");

        for (int i = 0; i < keyValuePairs.length; i++) {
            String pair = keyValuePairs[i];
            String[] keyValue = pair.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        log(map.toString());
    }

    /**
     * ???
     */

    public Map<String, Object> toMap(String mapContent) {
        final String mapPrefix = "map:{";
        final String mapSuffix = "}";
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> tempMap = new HashMap<String, Object>();
        List<String> keyValuePairs = new ArrayList<String>();
        //:は{}の中にいるかどうかを判断する
        boolean inQuotes = false;
        int startPos = 0;

        String blankReplaceString = mapContent.replace(" ", "");

        String mapString = mapContent.replace(" ", "").substring(blankReplaceString.indexOf(mapPrefix) + mapPrefix.length(), blankReplaceString.lastIndexOf(mapSuffix));

        //keyとvalueをstringから取り出す、valueがmapでも全部取り出す
        for(int currentPos = 0; currentPos < mapString.length(); currentPos++) {
            if (mapString.charAt(currentPos) == '{') inQuotes = !inQuotes;
            if (mapString.charAt(currentPos) == '}') inQuotes = !inQuotes;
            boolean atLastChar = (currentPos == mapString.length() - 1);
            if (atLastChar)
                keyValuePairs.add(mapString.substring(startPos));
            else if (mapString.charAt(currentPos) == ';' && !inQuotes) {
                keyValuePairs.add(mapString.substring(startPos, currentPos));
                startPos = currentPos + 1;
            }
        }

        //再帰でmapを構成する
        for (int i = 0; i < keyValuePairs.size(); i++) {
            String keyValue[] = keyValuePairs.get(i).split("=", 2);
            String key = keyValue[0];
            String value = keyValue[1];

            if(value.contains(mapPrefix)) {
                tempMap = toMap(value);
                value = tempMap.toString();
            }
            map.put(key,value);
        }
        return map;
    }

    /**
     * "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }" <br />
     * という文字列をMapに変換してtoString()すると？ <br />
     * <br />
     * "map:{ key1 = value1 ; key2 = value2 ; key3 = map:{ nkey31 = nvalue31 ; nkey32 = nvalue32 } }" <br />
     * でも、同じプログラムでMapに変換できるようにするべし。
     */
    public void test_parseMap_deep() throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        String mapString = "map:{ key1 = value1 ; key2 = map:{ nkey21 = nvalue21 ; nkey22 = nvalue22 } ; key3 = value3 }";
        String mapString2 = "map:{ key1 = value1 ; key2 = map:{ nkey1 = map:{ nnkey1 = map:{ nnnkey1 = map:{ nnnnkey1 = nnnnvalue1 } } } ; nkey2 = nvalue2 } ; key3 = value3 }";
        retMap = toMap(mapString2);
        log(retMap.toString());
    }
}
