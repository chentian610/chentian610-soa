package com.amazon;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.ArrayList;
import java.util.List;

@Gecco(matchUrl="https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords={keywords}&page={page}", pipelines={"allSortPipeline3"})
public class AllSortAmazon implements HtmlBean {

	private static final long serialVersionUID = 665662335318691818L;
	
	@Request
	private HttpRequest request;


	@RequestParameter("keywords")
	private String keywords;//url中的{user}值

	@RequestParameter("page")
	private String page;//url中的{project}值

	private String brand;//url中的{project}值

	//家用电器
	@Attr("data-asin")
	@HtmlField(cssPath=".s-result-list-parent-container ul li")
	private List<String> asin_list;


	//家用电器
	@HtmlField(cssPath=".s-result-list-parent-container ul li")
	private List<Product> product_list;

	public List<String> getAsin_list() {
		return asin_list;
	}

	public void setAsin_list(List<String> asin_list) {
		this.asin_list = asin_list;
	}


	public List<Product> getProduct_list() {
		return product_list;
	}

	public void setProduct_list(List<Product> product_list) {
		this.product_list = product_list;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}


	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public static void main(String[] args) {
		getAmazonRankByKeyWords("blanket+scarf");
	}

	public static void getAmazonRankByKeyWords(String keywords) {
//		this.brand = brand;

		//先获取分类列表
		HttpGetRequest start = new HttpGetRequest("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords="+keywords+"&page=1");
		start.setCharset("GBK");
		GeccoEngine.create()
				.classpath("com.amazon")
				//开始抓取的页面地址
				.start(start)
				//开启几个爬虫线程
				.thread(1)
				//单个爬虫每次抓取完一个请求后的间隔时间
//		.interval(2000)
				.run();

		//分类列表下的商品列表采用3线程抓取
		GeccoEngine.create()
				.classpath("com.amazon")
				//开始抓取的页面地址
				.start(AllSortPipeline.sortRequests)
				//开启几个爬虫线程
				.thread(3)
				//单个爬虫每次抓取完一个请求后的间隔时间
//				.interval(2000)
				.start();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
