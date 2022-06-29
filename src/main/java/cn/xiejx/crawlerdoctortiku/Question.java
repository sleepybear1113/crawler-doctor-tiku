package cn.xiejx.crawlerdoctortiku;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2022/06/29 22:05
 */
@Data
public class Question implements Serializable {
    @Serial
    private static final long serialVersionUID = 768642164169258962L;

    private String analyse;
    private String content;
    private String id;
    private String lableType;

    private List<QuestionKey> questionKeyList;

    public String parseString() {
        return this.id + "\t" + lableType + "\t" + content + "\t" + parseQuestionListString() + "\t解析：" + analyse;
    }

    public String parseQuestionListString() {
        if (CollectionUtils.isEmpty(questionKeyList)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (QuestionKey questionKey : questionKeyList) {
            s.append(questionKey.parseString()).append("\t");
        }
        return s.toString();
    }
}
