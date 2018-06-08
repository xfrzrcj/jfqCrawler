package com.mutliOrder.JiFenQiang.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class QinakaController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QinakaController.class);

	@ResponseBody
	@RequestMapping(value = "/keyServices/assistant.getInfo", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getInfo() {
				return "{\"payload\":{\"bundle_id\":\"com.aimizhushou.app\",\"displayName\":\"鐖辩背鍔╂墜\",\"scheme_qk\":\"com.aimizhushou.app\",\"scheme\":\"com.aimizhushou.app\"},\"status\":\"ok\"}";
				}
	
	@ResponseBody
	@RequestMapping(value = "/s4k/subtask.init", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String init() {
				return "{\"err_code\":40101,\"err_msg\":\"未登录\",\"payload\":{}}";
				}
	
	@ResponseBody
	@RequestMapping(value = "/s4k/subtask.list", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String list() {
				return "{\"err_code\":40101,\"err_msg\":\"未登录\",\"payload\":{}}";
				}
	@ResponseBody
	@RequestMapping(value = "/keyServices/updateState.once", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String updateState() {
				return "{\"payload\":{},\"status\":\"ok\"}";
				}
}
