package com.mdjuniooor.shop.mall.interceptor;

import com.mdjuniooor.shop.mall.domain.ProductCat;
import com.mdjuniooor.shop.mall.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    private CategoryService categoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // ajax 요청인지 아닌지 판단 (ajax 요청이 아닐 경우 if문 수행)
        if (!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            List<ProductCat> catList = categoryService.getCategoryList();

            log.info("#### 인터셉터/ 카테고리 리스트 : {}", catList);

        }
    }
}

