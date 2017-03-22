package com.sensir.studio.data.source;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Leon on 2017-3-18.
 * <p>
 * 切换或设置数据源的时候利用注解@DataSource({数据源名称})
 * 来设置当前方法或类下的为哪个数据源！
 * </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value();
}
