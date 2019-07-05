package com.yf.completetools.obejct;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;

import java.util.Objects;

/**
 * @Author: yefei
 * @Date: create in 2019-07-05
 * @Desc:
 */
@Api("对象操作工具类")
public class ObjectTool {

    /**
     * 判断两个对象是否相等, 可以用来判断基本类型的包装类型 ，避免空指针异常
     *
     * @param a
     * @param b
     * @return
     */
    @ApiOperation("判断两个对象是否相等")
    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }




}
