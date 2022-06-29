package cn.xiejx.crawlerdoctortiku;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2022/06/29 21:02
 */
public class Main {
    public static void main(String[] args) {

//        String qid = "755619,717345,739761,1127650,751605,1034675,1127592";
        String qid = "751605,1034675,1127592,755233,1056888,755131,1129843,1045355,751598,756992,1020236";
        GetQuestions.get2("122691", "109271", "65155", qid, "");
    }
}
