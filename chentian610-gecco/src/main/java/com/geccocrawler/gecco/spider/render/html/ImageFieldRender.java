package com.geccocrawler.gecco.spider.render.html;

import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.downloader.DownloaderContext;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.render.FieldRender;
import com.geccocrawler.gecco.spider.render.FieldRenderException;
import com.geccocrawler.gecco.utils.DownloadImage;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 渲染@Image属性
 * 
 * @author huchengyi
 *
 */
public class ImageFieldRender implements FieldRender {

	@Override
	@SuppressWarnings("unchecked")
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Set<Field> imageFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(Image.class));
		for (Field imageField : imageFields) {
			Object value = injectImageField(request, beanMap, bean, imageField);
			if(value != null) {
				fieldMap.put(imageField.getName(), value);
			}
		}
		beanMap.putAll(fieldMap);
	}

	private Object injectImageField(HttpRequest request, BeanMap beanMap, SpiderBean bean, Field field) {
		Object value = beanMap.get(field.getName());
		if(value == null) {
			return null;
		}
		String imgUrl = value.toString();
		if(StringUtils.isEmpty(imgUrl)) {
			return imgUrl;
		}
		Image image = field.getAnnotation(Image.class);
		String parentPath = image.download();
		if(StringUtils.isEmpty(parentPath)) {
			return imgUrl;
		}
		HttpResponse subReponse = null;
		try {
			String before =  StringUtils.substringBefore(imgUrl, "?");
			String last =  StringUtils.substringAfter(imgUrl, "?");
			String fileName = StringUtils.substringAfterLast(before, "/");
			if(StringUtils.isNotEmpty(last)) {
				last = URLEncoder.encode(last, "UTF-8");
				imgUrl = before + "?" + last;
			}
			HttpRequest subRequest = request.subRequest(imgUrl);
			subReponse = DownloaderContext.defaultDownload(subRequest);
			return DownloadImage.download(parentPath, fileName, subReponse.getRaw());
		} catch (Exception ex) {
			//throw new FieldRenderException(field, ex.getMessage(), ex);
			FieldRenderException.log(field, "download image error : " + imgUrl, ex);
			return imgUrl;
		} finally {
			if(subReponse != null) {
				subReponse.close();
			}
		}
	}
}
