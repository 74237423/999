package cts.phase3.dataenum;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-04 09:00
 **/
public enum Sex {

    MAle("男"), FEMALE("女"), NULL("无");
    public final String value;

    Sex(String value) {
        this.value = value;
    }
}
