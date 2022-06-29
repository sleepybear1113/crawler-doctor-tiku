package cn.xiejx.crawlerdoctortiku;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2022/06/29 22:06
 */
@Data
public class QuestionKey implements Serializable {
    @Serial
    private static final long serialVersionUID = -1914231160910566260L;

    private String content;
    private String id;
    private Boolean isnot_true;
    private String question_id;
    private String seq;

    public String parseString() {
        return content + "【%s】".formatted(Boolean.TRUE.equals(isnot_true) ? "正确" : "错误");
    }
}
