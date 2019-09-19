package com.xkcoding.spelaopdemo.support;

import com.xkcoding.spelaopdemo.enums.HttpMethod;
import com.xkcoding.spelaopdemo.model.Rule;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 规则注册
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 14:10
 */
@Data
public class SecureRuleRegistry {
    private List<Rule> ruleList = new ArrayList<>();

    public SecureRuleRegistry addRule(String path, HttpMethod method, String expression) {
        ruleList.add(new Rule(path, method, expression));
        return this;
    }
}
