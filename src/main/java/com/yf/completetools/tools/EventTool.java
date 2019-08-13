package com.yf.completetools.tools;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.yf.completetools.Application;
import io.swagger.annotations.Api;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yefei
 * @Date: create in 2019-08-13
 * @Desc:
 */
@Api("事件工具类")
public class EventTool {
    private static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    private static EventBus eventBus = new AsyncEventBus(threadPoolExecutor);

    /**
     * 扫描所有 Subscribe 的注解并将所在的类注册到 eventBus 中
     */
    static {
        CommonTool.getAllClassByPackageName(Application.class.getPackage()).stream().forEach(aClass -> {
            Method[] methods = aClass.getDeclaredMethods();
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    Subscribe annotation = method.getAnnotation(Subscribe.class);
                    if (annotation != null) {
                        try {
                            eventBus.register(aClass.newInstance());
                            break;
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * 事件发布方法
     *
     * @param event
     */
    public static void publishEvent(Object event) {
        eventBus.post(event);
    }

}
