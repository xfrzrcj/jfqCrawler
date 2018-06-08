package com.mutliOrder.JiFenQiang.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 8410端口 试客小兵
 */
@Controller
public class shikeController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(shikeController.class);

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String getInfo() {

		return "qLgKdimlcExmxxYNhcKmZqqCou0Iv+zG3uFq7djJtV5nCaN1v2PQSbSu2EWwzggiklZYoP93lIjPN7+CDrAGjva00dLNW9VExRmyprZrqSU/TvIzrG8gwPCd44cyNe8QJwfPl5CMqH531R0BQvvmnwjVvkbYWHYxnWKmG6s9HgUHQOzRUfEwG2nv6/xG011DR06NGRHwA4DgZCIf/Ko7arczJOY+KXFbwII4l6fAKoSLaG+dtkPVgy0U7vcviu6fVMgcD9UtdVo=";
	}

	@ResponseBody
	@RequestMapping(value = "/appstate", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String appstate(@RequestParam(required = true) String bundleid) {
		return "{\"appstate\":{\"state\":0,\"progress\":0}}";
	}
}
