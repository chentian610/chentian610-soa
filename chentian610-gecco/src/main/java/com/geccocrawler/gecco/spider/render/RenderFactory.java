package com.geccocrawler.gecco.spider.render;

import com.geccocrawler.gecco.spider.render.html.HtmlRender;
import com.geccocrawler.gecco.spider.render.json.JsonRender;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public abstract class RenderFactory {
	
	private Map<RenderType, Render> renders;
	
	public RenderFactory(Reflections reflections) {
		CustomFieldRenderFactory customFieldRenderFactory = new CustomFieldRenderFactory(reflections);
		renders = new HashMap<RenderType, Render>();
		
		AbstractRender htmlRender = createHtmlRender();
		htmlRender.setCustomFieldRenderFactory(customFieldRenderFactory);
		
		AbstractRender jsonRender = createJsonRender();
		jsonRender.setCustomFieldRenderFactory(customFieldRenderFactory);
		
		renders.put(RenderType.HTML, htmlRender);
		renders.put(RenderType.JSON, jsonRender);
	}
	
	public Render getRender(RenderType type) {
		return renders.get(type);
	}
	
	public abstract HtmlRender createHtmlRender();
	
	public abstract JsonRender createJsonRender();
	
}
