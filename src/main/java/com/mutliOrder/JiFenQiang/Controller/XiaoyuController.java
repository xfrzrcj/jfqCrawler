package com.mutliOrder.JiFenQiang.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XiaoyuController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(shikeController.class);

	@ResponseBody
	@RequestMapping(value = "/xyping", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String xyping(@RequestParam(required=false)String callback) {
		return callback+"({\"result\":\"200\",\"idfa\":\"0695AFA8-A3F1-4B61-830E-9DDCFD31AB58\",\"msg\":\"成功\",\"now\":\"0695AFA8-A3F1-4B61-830E-9DDCFD31AB58\"})";
	}
	
	@ResponseBody
	@RequestMapping(value = "/xyinfo", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String xyinfo(@RequestParam(required=false)String callback) {
		return callback+"({\"result\":\"200\",\"ios_version\":\"10.0.2\",\"now_idfa\":\"0695AFA8-A3F1-4B61-830E-9DDCFD31AB58\",\"msg\":\"成功\",\"bundleId\":\"com.xiaoyu.qian\",\"version\":\"3.0.5\"})";
	}
	
}
