package io.github.ordinarykai.constant;

/**
 * 系统常量
 *
 * @author kai
 * @date 2022/3/12 13:49
 */
public interface CommonConstant {

    String TOKEN = "token";

    /**
     * 默认密码
     */
    String DEFAULT_PWD = "123456";

    /**
     * 用户登录过期时间，单位s
     */
    long EXPIRE_TIME = 24 * 60 * 60;

    /**
     * java.time.LocalTime格式化文本
     */
    String LOCAL_TIME_PATTERN = "HH:mm:ss";

    /**
     * java.time.LocalDate格式化文本
     */
    String LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * java.time.LocalDateTime格式化文本
     */
    String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 禁用
     */
    Integer DISABLE = 0;

    /**
     * 启用标志
     */
    Integer ENABLE = 1;

    /**
     * 异常信息：系统异常，该数据不存在
     */
    String DATA_NOT_FOUND = "系统异常，该数据不存在";

    /**
     * 正式环境标识符
     */
    String PRO_ENV = "run";

}
