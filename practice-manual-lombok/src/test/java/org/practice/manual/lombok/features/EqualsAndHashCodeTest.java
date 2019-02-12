package org.practice.manual.lombok.features;

import java.util.Date;

import org.junit.Test;
import org.practice.manual.lombok.vo.UserBaseVo;

public class EqualsAndHashCodeTest {

    @Test
    public void test() {
        UserBaseVo vo1 = new UserBaseVo();
        vo1.setCreateTime(new Date());
        vo1.setUserId(2L);

        UserBaseVo vo2 = new UserBaseVo();
        vo2.setCreateTime(new Date());
        vo2.setUserId(2L);

        System.out.println(vo1.equals(vo2));
        System.out.println(vo1);
    }
}
