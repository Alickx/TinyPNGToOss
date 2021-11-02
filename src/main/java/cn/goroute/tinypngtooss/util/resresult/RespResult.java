package cn.goroute.tinypngtooss.util.resresult;

/**
 * @Author Alickx
 * @Date 2021/10/31 11:05
 * 响应结果生成工具
 */
public class RespResult {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result success() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> success(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result fail(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
}
