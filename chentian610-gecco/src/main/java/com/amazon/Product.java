package com.amazon;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

public class Product implements HtmlBean {

	private static final long serialVersionUID = 3018760488621382659L;

	@Text
	@HtmlField(cssPath="div div:nth-child(3) div:nth-child(1) a h2")
	private String title;

	@Text
	@HtmlField(cssPath="div div:nth-child(2) div a span")
	private String brand;//抽取页面中的fork

	@Attr("data-asin")
	private String asin;//抽取页面中的fork

	public String getSponsored_brand() {
		return sponsored_brand;
	}

	public void setSponsored_brand(String sponsored_brand) {
		this.sponsored_brand = sponsored_brand;
	}

	public String getSponsored_title() {
		return sponsored_title;
	}

	public void setSponsored_title(String sponsored_title) {
		this.sponsored_title = sponsored_title;
	}

	public String getSponsored_brand_log() {
		return sponsored_brand_log;
	}

	public void setSponsored_brand_log(String sponsored_brand_log) {
		this.sponsored_brand_log = sponsored_brand_log;
	}

	@Attr("title")
	@HtmlField(cssPath="div div:nth-child(2) div a img")
	private String brand_logo;//抽取页面中的fork

	@Text
	@HtmlField(cssPath="div h5")
	private String sponsored;//抽取页面中的fork

	@Text
	@HtmlField(cssPath="div div:nth-child(2) div a span")
	private String sponsored_brand;//抽取页面中的fork

	@Text
	@HtmlField(cssPath="div h5")
	private String sponsored_title;//抽取页面中的fork

	@Text
	@HtmlField(cssPath="div h5")
	private String sponsored_brand_log;//抽取页面中的fork

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrand_logo() {
		return brand_logo;
	}

	public void setBrand_logo(String brand_logo) {
		this.brand_logo = brand_logo;
	}

	public String getSponsored() {
		return sponsored;
	}

	public void setSponsored(String sponsored) {
		this.sponsored = sponsored;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
