package com.xkcoding.spelaopdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 17:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 权限标识
     */
    private Set<String> permissions;
}
