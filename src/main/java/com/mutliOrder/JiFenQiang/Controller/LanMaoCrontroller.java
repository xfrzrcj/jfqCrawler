package com.mutliOrder.JiFenQiang.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 35941端口，懒猫
 * @author Administrator
 *
 */
@Controller
public class LanMaoCrontroller {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LanMaoCrontroller.class);

	//
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String login() {
		return "{\"status\":\"1\",\"data\":{\"idfa\":\"0695AFA8-A3F1-4B61-830E-9DDCFD31AB58\",\"type\":\"2\",\"newlogin\":true,\"openudid\":\"e05b0d40625f4f746c386eed4ae35f57f1b33643\"},\"msg\":\"ok\",\"error\":\"\"}";
	}

	@ResponseBody
	@RequestMapping(value = "/successloginwanpan", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String successloginwanpan() {
		return "{\"status\":\"1\",\"data\":{\"isLogin\":true,\"from\":\"com.mazhang.app\"},\"msg\":\"ok\",\"error\":\"\"}";
	}

	@ResponseBody
	@RequestMapping(value = "/getappinfo", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String getappinfo() {
		return "{\"status\":\"1\",\"data\":{\"version\":\"4.3.4\",\"setupchannel\":\"1\",\"osversion\":\"10.0.2\"},\"msg\":\"ok\",\"error\":\"\"}";
	}

	@ResponseBody
	@RequestMapping(value = "/allapps", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String allapps() {
		return "{\"status\":\"1\",\"data\":{},\"msg\":\"ok\",\"error\":\"\"}";
	}

}
