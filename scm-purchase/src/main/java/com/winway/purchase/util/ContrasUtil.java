package com.winway.purchase.util;

import io.swagger.annotations.ApiModelProperty;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取修改后不同的字段
 * 转成字符串
 *
 * @param 修改字段 传入的参数为原数据对象，新数据对象，数据ID，数据类型
 * @return
 */
public class ContrasUtil {

    public static String Contrast(Object oldBean, Object newBean, String id, String category) throws Exception {
        StringBuffer str = new StringBuffer();
        Field[] fields = oldBean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }
            //获取Bean字段注解value
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            Method getmeMethod = new PropertyDescriptor(field.getName(), oldBean.getClass()).getReadMethod();
            Object o1 = getmeMethod.invoke(oldBean);
            Object o2 = getmeMethod.invoke(newBean);
            if (o1 == null || o2 == null) {
                continue;
            }
            if (!o1.toString().equals(o2.toString())) {
                str.append(apiModelProperty.value() + "：" + o1 + "->" + o2 + "-" + id + "-" + category + ";");
            }
        }
        return str.toString();
    }
}
