package com.ycodify.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTotalTimeFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostTotalTimeFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Post filter entered");

		Long initialTime = (Long) request.getAttribute("initialTime");
		Long finalTime = System.currentTimeMillis();
		Long totalTime = finalTime - initialTime;

		log.info(String.format("Total time in seconds: %s", totalTime.doubleValue()/1000.0));
		log.info(String.format("Total time in miliseconds: %s", totalTime));

		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
