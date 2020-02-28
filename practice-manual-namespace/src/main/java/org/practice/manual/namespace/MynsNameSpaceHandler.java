package org.practice.manual.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MynsNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("mybean", new MybeanParser());
    }

}
