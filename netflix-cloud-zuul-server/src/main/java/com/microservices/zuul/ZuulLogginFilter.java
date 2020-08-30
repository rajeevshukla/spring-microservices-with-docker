package com.microservices.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLogginFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(ZuulLogginFilter.class);

	@Override
	public boolean shouldFilter() {
	
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("request -> {} rquestURI -> {}",request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre"; //post // error;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
