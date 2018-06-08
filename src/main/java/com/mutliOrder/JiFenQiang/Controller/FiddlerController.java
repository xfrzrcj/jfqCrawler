package com.mutliOrder.JiFenQiang.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mutliOrder.JiFenQiang.Utils.HttpClientUtils;

/**
 * 由fiddler过滤请求转发到8080端口，在这里处理请求。
 * 此处将请求发送到应有的地址后，获取返回参数记录并返回，以供爬虫参考
 * @author Administrator
 *
 */
@Controller
public class FiddlerController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(shikeController.class);
	
	/**
	 * 试客小兵，任务详情数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shike/api/appDetail", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String xyping(HttpServletRequest request) {
		Map<String,String> header = new HashMap<String,String>();
		Cookie[] cookies = request.getCookies();
		StringBuffer cookieStr = new StringBuffer();
		for(Cookie cookie:cookies) {
			cookieStr.append(cookie.getName()+"="+cookie.getValue()+";");
		}
		int len = cookieStr.length();
		cookieStr.deleteCharAt(len-1);
		header.put("Cookie", cookieStr.toString());
		header.put("User-Agent", request.getHeader("User-Agent"));
		header.put("Accept", request.getContentType());
		String res = HttpClientUtils.httpGet(request.getRequestURL().toString()+"?"+request.getQueryString(), header);
		//TODO
		System.out.println(res);
		return res;
	}
	
}
