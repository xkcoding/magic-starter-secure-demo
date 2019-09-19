package com.xkcoding.spelaopdemo.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.xkcoding.spelaopdemo.constants.SecureConstants;
import com.xkcoding.spelaopdemo.exception.SecureException;
import com.xkcoding.spelaopdemo.model.SecureUser;
import com.xkcoding.spelaopdemo.support.UserContextHolder;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 鉴权工具类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 17:08
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecureUtil {
    private static final int SEVEN = 7;
    private final JwtUtil jwtUtil;

    /**
     * 返回当前用户信息
     *
     * @return 当前用户信息
     */
    public SecureUser getCurrentUser() {
        SecureUser secureUser = UserContextHolder.get();
        if (secureUser == null) {
            // 从 token 里获取
            HttpServletRequest request = getRequest();
            String token = getTokenFromRequest(request);
            // 校验 token 是否合法
            Boolean isValid = jwtUtil.validateToken(token);
            if (!isValid) {
                return null;
            }

            secureUser = getUserFromToken(token);
            UserContextHolder.set(secureUser);
        }
        return secureUser;
    }

    /**
     * 解析token，获得用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @SuppressWarnings("unchecked")
    private SecureUser getUserFromToken(String token) {
        Claims claims = jwtUtil.getClaimsFromToken(token);
        Object userId = claims.get(JwtUtil.USER_ID);
        Object username = claims.get(JwtUtil.USERNAME);
        Object permissions = claims.get(JwtUtil.PERMISSIONS);

        return SecureUser.builder().id(Convert.toLong(userId)).username((String) username).permissions(CollUtil.newHashSet((List<String>) permissions)).build();
    }

    /**
     * 从request中获取token
     *
     * @param request 请求
     * @return token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(SecureConstants.AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(header)) {
            throw new SecureException("没有找到名为Authorization的header");
        }
        if (header.length() <= SEVEN) {
            throw new SecureException("token非法，长度 <= 7");
        }

        if (!header.startsWith(SecureConstants.BEARER)) {
            throw new SecureException("token必须以'Bearer '开头");
        }

        return header.substring(SEVEN);
    }

    /**
     * 获取request
     *
     * @return request
     */
    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if ((requestAttributes == null)) {
            throw new SecureException("当前不是web环境");
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

}
