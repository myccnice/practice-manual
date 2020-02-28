package org.practice.manual.namespace;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * https://blog.csdn.net/m0_38043362/article/details/78329188
 *
 * @author 王鹏
 * @date 2019年5月6日
 */
public class MybeanNamespaceTestCase {

    @Test
    public void testGetBean(){

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"myapp.xml"},false);
        ac.setValidating(false);
        ac.refresh();
        People bean = ac.getBean("mybean123",People.class);
        System.out.println(bean.getName());
        ac.close();
    }
}
