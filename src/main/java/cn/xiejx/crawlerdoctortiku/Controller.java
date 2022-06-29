package cn.xiejx.crawlerdoctortiku;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2022/06/29 23:00
 */
@RestController
public class Controller {

    @RequestMapping("/go")
    public void go(String examId, String courseId, String paperId, String qid, String key) {
        new Thread(() -> GetQuestions.get2(examId, courseId, paperId, qid, key)).start();
    }

    @RequestMapping("/delay")
    public void delay(Long delay) {
        Constants.DELAY.put("delay", delay);
    }
}
