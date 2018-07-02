package com.cheng.web;

import com.cheng.entity.system.UserDO;
import com.cheng.util.SessionAttributeNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
/**
 * @author miaocheng
 * @date 2018/05/29
 *
 */
@Controller("baseController")
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected UserDO getLoginUser(HttpServletRequest request) {
        UserDO userDO = (UserDO) WebUtils.getSessionAttribute(request, SessionAttributeNames.LOGIN_USER);
        return userDO;
    }
}
