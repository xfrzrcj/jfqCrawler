package com.mutliOrder.JiFenQiang.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WanFouController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WanFouController.class);

	@ResponseBody
	@RequestMapping(value = "/getClient_no", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String xyping(@RequestParam(required=false)String callback) {
		return callback+"({\"client_no\":\"YMfb7Jt5LVgUN0vYbS0XOg\",\"idfa\":\"0695AFA8-A3F1-4B61-830E-9DDCFD31AB58\"})";
	}
}
