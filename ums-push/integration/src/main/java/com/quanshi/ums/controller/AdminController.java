/**
 * 
 */
package com.quanshi.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理控制台
 * 
 * @author yanxiang.huang 2017-06-15 10:45:53
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController
{

    @RequestMapping
    public String index(Model model) {
        
        return "index";
    }
}
