package org.practice.manual.lombok.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(doNotUseGetters=true,callSuper=true)
public class UserBaseVo extends BaseVo {

    private Long userId;

    public Long getUserId() {
        if (userId == 0) {
            return 1L;
        }
        return 0L;
    }
}
