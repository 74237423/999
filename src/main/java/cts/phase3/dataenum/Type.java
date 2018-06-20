package cts.phase3.dataenum;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-04 08:52
 **/
public enum Type {
    FIGURE("人物"), ANIMAL("动物"), SCENERY("风景"), CRATOON("卡通");

    public final String value;

    Type(String value) {
        this.value = value;
    }
}
