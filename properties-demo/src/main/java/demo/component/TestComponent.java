package demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 测试鉴权类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 16:42
 */
@Slf4j
@Component("test")
public class TestComponent {
    /**
     * 自定义鉴权方法
     *
     * @param request  request
     * @param response response
     * @return {@code true} / {@code false}
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(HttpServletRequest request, HttpServletResponse response) {
        log.info("【request】= {}", request);
        log.info("【response】= {}", response);
        // 可以根据拿到的 request，跟数据库/缓存里的 用户-角色-权限 等关系判断是否拥有相应权限
        return true;
    }
}
