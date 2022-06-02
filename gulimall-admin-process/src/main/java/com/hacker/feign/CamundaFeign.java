package com.hacker.feign;

import com.hacker.common.result.R;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/26 08:54
 * @Description:
 */
//@FeignClient(value = "gulimall-product-pro")
public interface CamundaFeign {

    @GetMapping("/product/category/list/tree")
    public R<List<?>> list();

}
