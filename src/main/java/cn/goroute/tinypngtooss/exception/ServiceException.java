package cn.goroute.tinypngtooss.exception;


/**
 * @Author Alickx
 * @Date 2021/10/31 11:19
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
