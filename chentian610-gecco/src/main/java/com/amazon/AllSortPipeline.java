package com.amazon;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;

import java.util.ArrayList;
import java.util.List;

@PipelineName("allSortPipeline3")
public class AllSortPipeline implements Pipeline<AllSortAmazon> {
	
	public static List<HttpRequest> sortRequests = new ArrayList<HttpRequest>();

	@Override
	public void process(AllSortAmazon allSort) {
		System.out.println("amazon process1.....");
		HttpRequest currRequest = allSort.getRequest();
		if ("1".equals(allSort.getPage())) {
			for(int i=2;i<11;i++) {
				sortRequests.add(currRequest.subRequest(currRequest.getUrl().replace("page=1","page="+i)));
				//SchedulerContext.into(currRequest.subRequest(url));
				//将分类的商品列表地址暂存起来
			}
		}
		List<Product> product_list = allSort.getProduct_list();
		List<String> asin_list = allSort.getAsin_list();
		int race=0,sponsored =0;
		for (int i =0;i<asin_list.size();i++) {
			String asin = asin_list.get(i);
			if (asin==null || "".equals(asin))
				continue;
			Product product =  product_list.get(i);
			if ("sponsored".equals(product.getSponsored())) {
				sponsored++;
				if (("soul young".equals(product.getSponsored_brand()) || "soul young".equals(product.getSponsored_brand_log())) && "sponsored".equals(product.getSponsored()))
					System.out.println("徐荣良的ASIN:["+asin+"],广告品牌:["+product.getSponsored_title()+"]关键字:["+allSort.getKeywords()+"]搜索排名在第"+allSort.getPage()+"页"+(sponsored)+"位");
			} else {
				race++;
				if ("soul young".equals(product.getBrand()) || "soul young".equals(product.getBrand_logo())) {
					System.out.println("徐荣良的ASIN:["+asin+"],内裤品牌:["+product.getTitle()+"]关键字:["+allSort.getKeywords()+"]搜索排名在第"+allSort.getPage()+"页"+race+"位");
				}
			}
		}
		System.out.println("第"+allSort.getPage()+"页Over");
	}

}