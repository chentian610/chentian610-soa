package com.geccocrawler.gecco.scheduler;

import com.geccocrawler.gecco.request.HttpRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 不需要循环抓取的start队列
 * 
 * @author huchengyi
 *
 */
public class NoLoopStartScheduler implements Scheduler {
	
	private ConcurrentLinkedQueue<HttpRequest> queue;
	
	public NoLoopStartScheduler() {
		queue = new ConcurrentLinkedQueue<HttpRequest>();
	}

	@Override
	public HttpRequest out() {
		HttpRequest request = queue.poll();
		return request;
	}

	@Override
	public void into(HttpRequest request) {
		queue.offer(request);
	}

}
