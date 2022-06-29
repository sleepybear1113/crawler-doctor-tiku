package cn.xiejx.crawlerdoctortiku;

import cn.xiejx.crawlerdoctortiku.http.HttpHelper;
import cn.xiejx.crawlerdoctortiku.http.HttpResponseHelper;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2022/06/29 22:16
 */
public class GetQuestions {
    public static final String URL = "http://xjhsdk.xjyxonline.com/dkexam/exam/%s_JSON/%s/%s/%s.json";
    public static Random random = new Random();

    public static String get(String examId, String courseId, String paperId, String qid) {
        String url = URL.formatted(examId, courseId, paperId, qid);
        System.out.println(url);
        HttpHelper httpHelper = new HttpHelper(url);
        HttpResponseHelper request = httpHelper.request();
        String responseBody = request.getResponseBody();
        System.out.println(responseBody);
        Question question = JSON.parseObject(responseBody, Question.class);
        return question.parseString();
    }

    public static void get(String examId, String courseId, String paperId, String[] qid) {
        for (String id : qid) {
            String s = get(examId, courseId, paperId, id);
            Util.writeFile(s, "questions.txt");
        }
    }

    public static void get2(String examId, String courseId, String paperId, String qid, String key) {
        String qs = Util.readFile(Constants.FILE_PATH);
        if (StringUtils.isBlank(qs)) {
            qs = "";
        }
        String[] lines = qs.split("\n");
        Set<String> qidSet = new HashSet<>();
        if (lines.length > 0) {
            for (String line : lines) {
                String[] split = line.split("\t");
                qidSet.add(split[0]);
            }
        }

        String[] split = qid.split(",");
        StringBuilder list = new StringBuilder();
        for (String id : split) {
            if (!"11112222".equals(key)) {
                if (random.nextInt(10000) > 3000) {
                    continue;
                }
            } else {
                if (!CollectionUtils.isEmpty(qidSet)) {
                    if (qidSet.contains(id)) {
                        continue;
                    }
                }
            }

            String s;
            try {
                s = get(examId, courseId, paperId, id);
            } catch (Exception e) {
                continue;
            }
            list.append(s).append("\n");
            try {
                Long delay = Constants.DELAY.get("delay");
                if (delay != null && delay > 0) {
                    System.out.println("休眠毫秒：" + delay);
                    TimeUnit.MILLISECONDS.sleep(delay);
                }
            } catch (InterruptedException ignored) {
            }
        }
        Util.writeFile(list.toString(), "questions.txt");
        System.out.println("================================");
        System.out.println("结束爬取");
    }
}
