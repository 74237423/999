package cts.phase3.dataenum;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 20:25
 **/
public enum Role {

    WORKER("工人"), ANNOUNCER("发布者"), RATER("评分工人"), ADMIN("管理员");

    public final String value;

    Role(String value) {
        this.value = value;
    }
}