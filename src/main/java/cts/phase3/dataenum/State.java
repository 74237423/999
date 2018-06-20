package cts.phase3.dataenum;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-03 22:48
 **/
public enum State {
    FINISH(0), UNFINISH(1);

    public final int value;

    State(int value) {
        this.value = value;
    }
}
