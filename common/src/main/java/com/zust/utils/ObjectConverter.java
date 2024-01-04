package com.zust.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy
 * @date 2023-7-16 016 0:03
 * <p>
 * PO VO 转换工具类
 */
public class ObjectConverter {

    /**
     * A类型对象转B类型对象
     *
     * @param A A类型对象
     * @param B B对象的Class类型(要转成的类型)
     * @return 转换后的B对象
     */
    public static <P, V> V AToB(P A, Class<V> B) {
        try {
            // 1. 通过voClass获取构造函数,实例化出一个V类型对象vo
            V vo = B.getDeclaredConstructor().newInstance();
            // 2. 使用BeanUtils工具类,将po对象的属性复制到vo对象中
            BeanUtils.copyProperties(A, vo);
            // 3. 返回转换后的V类型对象vo
            return vo;
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 作用同上，但是是处理list用的
     * List<A> ==> List<B>
     */
    public static <P, V> List<V> listAToB(List<P> A, Class<V> B) {
        List<V> voList = new ArrayList<>();

        for (P po : A) {
            V vo = AToB(po, B);
            voList.add(vo);
        }

        return voList;
    }


}
