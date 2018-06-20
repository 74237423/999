package cts.phase3.dataenum;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-07 19:47
 **/
public enum Ranking {

    FIRST("初学弟子"), SECOND("初入江湖"), THIRD("江湖新秀"), FOURTH("江湖少侠"), FIFTH("江湖大侠"), SIXTH("一代掌门"),
    SEVENTH("一代宗师"), EIGHTH("武林盟主");

    public final String value;

    Ranking(String value) {
        this.value = value;
    }
}
