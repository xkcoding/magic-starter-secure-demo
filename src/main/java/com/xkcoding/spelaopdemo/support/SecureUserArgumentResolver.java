package com.xkcoding.spelaopdemo.support;

import com.xkcoding.spelaopdemo.annotation.CurrentUser;
import com.xkcoding.spelaopdemo.model.SecureUser;
import com.xkcoding.spelaopdemo.util.SecureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <p>
 * 参数绑定解析
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/19 10:07
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecureUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final SecureUtil secureUtil;

    /**
     * 参数上必须包含 {@link CurrentUser} 注解，同时满足参数类型是 {@link SecureUser}
     *
     * @param parameter 参数
     * @return 满足条件 true，反之 false
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(SecureUser.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    /**
     * 解析参数
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return secureUtil.getCurrentUser();
    }
}
