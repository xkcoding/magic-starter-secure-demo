package com.xkcoding.spelaopdemo.model;

import com.xkcoding.spelaopdemo.enums.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 规则
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 11:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    /**
     * 路径
     */
    private String path;
    /**
     * 请求方法
     */
    private HttpMethod method;
    /**
     * spel表达式
     */
    private String expression;
}
