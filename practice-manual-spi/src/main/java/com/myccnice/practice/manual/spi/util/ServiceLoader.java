package com.myccnice.practice.manual.spi.util;

import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ServiceConfigurationError;

/**
 * 自己实现的ServiceLoader，JDK中有同名类
 *
 * create in 2018年8月30日
 * @author wangpeng
 * @see java.util.ServiceLoader
 */
public final class ServiceLoader<S> implements Iterable<S> {

    private static final String PREFIX = "META-INF/services/";

    private final Class<S> service;

    private final ClassLoader loader;

    private LinkedHashMap<String,S> providers = new LinkedHashMap<>();

    private LazyIterator lookupIterator;
    public void reload() {
        providers.clear();
        lookupIterator = new LazyIterator(service, loader);
    }

    private ServiceLoader(Class<S> svc, ClassLoader cl) {
        service = Objects.requireNonNull(svc, "Service interface cannot be null");
        loader = (cl == null) ? ClassLoader.getSystemClassLoader() : cl;
        reload();
    }

    private class LazyIterator implements Iterator<S> {
        Class<S> service;
        ClassLoader loader;
        Enumeration<URL> configs = null;
        // 存放META-INF/services/下文件的内容，一行一条记录
        Iterator<String> pending = null;
        String nextName = null;

        private LazyIterator(Class<S> service, ClassLoader loader) {
            this.service = service;
            this.loader = loader;
        }

        private boolean hasNextService() {
            if (nextName != null) {
                return true;
            }
            // 读取配置文件
            if (configs == null) {
                // 每个接口一个配置文件
                String fullName = PREFIX + service.getName();
                // 读取 META-INF/services/下的配置文件
                configs = FileUtil.getConfig(fullName, loader);
            }
            if (!configs.hasMoreElements()) {
                return false;
            }
            if ((pending == null) || !pending.hasNext()) {
                // 将配置文件的每一行内容读到pending中保存
                pending = FileUtil.readLines(configs.nextElement()).iterator();
            }
            nextName = pending.next();
            return true;
        }

        private S nextService() {
            if (!hasNextService())
                throw new NoSuchElementException();
            String cn = nextName;
            nextName = null;
            Class<?> c = null;
            try {
                // 在遍历的时候实例化具体的服务提供者
                c = Class.forName(cn, false, loader);
            } catch (ClassNotFoundException x) {
                throw new ServiceConfigurationError("");
            }
            // 检查实例化的对象service是否是指定类c的子类或者同类
            if (!service.isAssignableFrom(c)) {
                throw new ServiceConfigurationError("");
            }
            try {
                S p = service.cast(c.newInstance());
                providers.put(cn, p);
                return p;
            } catch (Throwable x) {
                throw new ServiceConfigurationError("");
            }
        }

        public boolean hasNext() {
            return hasNextService();
        }

        public S next() {
            return nextService();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public Iterator<S> iterator() {
        return new Iterator<S>() {
            Iterator<Map.Entry<String,S>> knownProviders = providers.entrySet().iterator();
            public boolean hasNext() {
                if (knownProviders.hasNext())
                    return true;
                return lookupIterator.hasNext();
            }

            public S next() {
                if (knownProviders.hasNext())
                    return knownProviders.next().getValue();
                return lookupIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static <S> ServiceLoader<S> load(Class<S> service, ClassLoader loader) {
        return new ServiceLoader<>(service, loader);
    }

    public static <S> ServiceLoader<S> load(Class<S> service) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return ServiceLoader.load(service, cl);
    }

}
