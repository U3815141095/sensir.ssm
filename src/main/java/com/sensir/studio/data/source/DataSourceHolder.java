package com.sensir.studio.data.source;

/**
 * Created by Leon on 2017-3-18.
 */
public class DataSourceHolder {
    public static final String DATA_SOURCE_A = "dataSourceA";
    public static final String DATA_SOURCE_B = "dataSourceB";
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();

    /**
     * 设置数据源
     *
     * @param dataSource 数据源
     */
    public static void setDataSource(String dataSource) {
        dataSourceHolder.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSource() {
        return dataSourceHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void removeDataSource() {
        dataSourceHolder.remove();
    }
}
