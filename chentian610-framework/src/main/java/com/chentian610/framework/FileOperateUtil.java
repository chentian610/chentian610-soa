/**
 *
 * @author chentian610
 * @date 2014-8-5 下午12:05:57
 */
package com.chentian610.framework;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.StringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.chentian610.common.Constants;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

/**
 * 
 * @author chentian610
 * @date 2014-8-5 下午12:05:57
 */
public class FileOperateUtil {
	/**
	 * 上传文件
	 * @author chentian610
	 * @param request http请求
	 * @param field 字段，
	 * @param saveDir 保存的路径
	 * @throws UnsupportedEncodingException 
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws Exception
	 */
	public static Map<String,String> upload(HttpServletRequest request,String saveDir) {
		Map<String,String> resultMap = new HashMap<String,String>();
		File dirPath = new File(saveDir);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		/** 创建磁盘文件对象 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		/** 设置缓冲区大小 */
		factory.setSizeThreshold(1024 * 1024 * 100);
		/** 创建文件获取对象 */
		ServletFileUpload upload = new ServletFileUpload(factory);
		/** 设置文件对象尺寸 */
		upload.setSizeMax(1024 * 1024 * 100);
		/** 得到所有的文件 */
		List<FileItem> items = null;
		upload.setHeaderEncoding(request.getCharacterEncoding());
		try {
			items = upload.parseRequest(request);
			if (items == null) return null;
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			return null;
		}
		Iterator<FileItem> i = items.iterator();
		while (i.hasNext()) {
			FileItem item = (FileItem) i.next();
			// 检查当前项目是普通表单项目还是上传文件。
			if (item.isFormField()) {
				try {
					resultMap.put(item.getFieldName(), item.getString(request.getCharacterEncoding()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				String fileName = item.getName();
				if (StringUtil.isEmpty(fileName)) continue;
				long fileSize=item.getSize();//文件大小
				String newFileID = getFileID();
				String fileStoreName=newFileID+fileName.substring(fileName.lastIndexOf("."));
				String fileResizeStoreName=newFileID+Constants.RESIZE+fileName.substring(fileName.lastIndexOf("."));
				//生成文件
				File savedFile = new File(dirPath, fileStoreName);
				
				//写入到硬盘
				try {
					item.write(savedFile);
			        int rotate = getRotate(savedFile);
			        if (rotate>0) rotateImage(dirPath+"/"+fileStoreName,rotate);
					Boolean resize = resizeFix(savedFile,dirPath+"/"+fileResizeStoreName);
					String pictureList = resultMap.get("fileURL");
					String fileNameList = resultMap.get("fileName");
					String fileResizeList = resultMap.get("fileResizeName");
					String fileSizeList=resultMap.get("fileSize");
					if (pictureList==null ) {
						resultMap.put("fileURL",fileStoreName);
						resultMap.put("fileName",fileName);
						resultMap.put("fileResizeName",resize?fileResizeStoreName:fileStoreName);
						resultMap.put("fileSize", fileSize+"");
					} else {
						resultMap.put("fileURL",pictureList+","+fileStoreName);
						resultMap.put("fileName",fileNameList+","+fileName);
						resultMap.put("fileResizeName",fileResizeList+","+(resize?fileResizeStoreName:fileStoreName));
						resultMap.put("fileSize", fileSizeList+","+fileSize);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					resultMap.put("fileURL", null);
				}
				resultMap.put("fileStoreName", fileStoreName);
			}
		}
		String user_id = StringUtil.getStringValue(resultMap.get("user_id"));
		String user_type= StringUtil.getStringValue(resultMap.get("user_type"));
		String school_id=StringUtil.getStringValue(resultMap.get("school_id"));
		if (!StringUtil.isEmpty(user_id))
			ActionUtil.getActionParam().setUser_id(Integer.parseInt(user_id));
		if (!StringUtil.isEmpty(user_type))
			ActionUtil.getActionParam().setUser_type(user_type);		
		if (!StringUtil.isEmpty(school_id))
			ActionUtil.getActionParam().setSchool_id(Integer.parseInt(school_id));	
		ActionUtil.getActionParam().setSysTime(new Date());
		return resultMap;
	}
	
	
	/**
	 * 获取文件ID，通过当前时间+随机数获得
	 * @return
	 * @throws Exception
	 */
	public static String getFileID() {
		System.out.println(Math.random());
		Long now = Long.parseLong(new SimpleDateFormat("yyMMddHHmmss")
				.format(new Date()));
		Long random = (long) (Math.random() * now);
		return now + "" + random;
	}
	
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public static Boolean resizeFix(File savedFile,String fileStoreName) { 
    	try {
    		BufferedImage img = ImageIO.read(savedFile);
			if (img==null) return false;
	    	Integer w = img.getWidth(null);    // 得到源图宽  
	    	Integer h = img.getHeight(null);
	    	
	        if (w<h) resizeByWidth(img,w,h,fileStoreName);  
	         else   resizeByHeight(img,w,h,fileStoreName);  
//	        int rotate = getRotate(savedFile);
//	        if (rotate>0) rotateImage(fileStoreName,rotate);
	        return true;
		} catch (IOException e) {
			return false;
		}
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public static void resizeByWidth(Image img,int w,int h,String fileStoreName) throws IOException {  
        int h1 = (int) (Constants.PICTURE_RESIZE_WIDTH * h / w);  
        //resize(img,Constants.PICTURE_RESIZE_WIDTH, h1,fileStoreName);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public static void resizeByHeight(Image img,int w,int h,String fileStoreName) throws IOException {  
        int w1 = (int) (Constants.PICTURE_RESIZE_HEIGHT * w / h);  
        //resize(img,w1, Constants.PICTURE_RESIZE_HEIGHT,fileStoreName);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    /*
    public static void resize(Image img,int w, int h,String fileStoreName) throws IOException {  
    	// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB); 
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(fileStoreName);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);  
        jep.setQuality(Constants.PICTURE_QUAILTY, true); 
        encoder.encode(image,jep); // JPEG编码  
        out.close();
    }*/
    
    /**
     * 获取图片正确显示需要旋转的角度（顺时针）
     * @return
     */
    public static int getRotate(File file){
    	try{
		        int rotate = 0;
		        Metadata metadata = ImageMetadataReader.readMetadata(file);  
		        int orientation = 0;
		        for (Directory directory : metadata.getDirectories()) {  
		        	if("ExifIFD0Directory".equalsIgnoreCase(directory.getClass().getSimpleName())){
		        		orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		        		break;
		        	}
		        } 
	           // 原图片的方向信息
	           if(6 == orientation ){
	               //6旋转90
	        	   rotate = 90;
	           }else if( 3 == orientation){
	              //3旋转180
	        	   rotate = 180;
	           }else if( 8 == orientation){
	              //8旋转90
	        	   rotate = 270;
	           }
	           return rotate; 
	    } catch (Exception e) {
	    	return 0;
	    }
    }
    
    /**
     * 旋转手机照片
     * @return
     */
    public static void rotateImage(String fullPath, int angel){
        BufferedImage src;
        try {
            src = ImageIO.read(new File(fullPath));
            int src_width = src.getWidth(null);
            int src_height = src.getHeight(null);
            Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
            BufferedImage res = new BufferedImage(rect_des.width, rect_des.height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = res.createGraphics();
            g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
            g2.drawImage(src, null, null);
            ImageIO.write(res, "jpg", new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
    
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {  
        // if angel is greater than 90 degree, we need to do some conversion  
        if (angel >= 90) {  
            if(angel / 90 % 2 == 1){  
                int temp = src.height;  
                src.height = src.width;  
                src.width = temp;  
            }  
            angel = angel % 90;  
        }  
  
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;  
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;  
        double angel_dalta_width = Math.atan((double) src.height / src.width);  
        double angel_dalta_height = Math.atan((double) src.width / src.height);  
  
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_width));  
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_height));  
        int des_width = src.width + len_dalta_width * 2;  
        int des_height = src.height + len_dalta_height * 2;  
        return new Rectangle(new Dimension(des_width, des_height));
    }
    
    	  
        /** 
         * 图片数据元提取 
         */  
        public static void metadataExtractor(){  
            File jpegFile = new File("G:/1.jpg");  
            try {  
                Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);  
                for (Directory directory : metadata.getDirectories()) {
                    	 if("ExifSubIFDDirectory".equalsIgnoreCase( directory.getClass().getSimpleName() )){  
                             //光圈F值=镜头的焦距/镜头光圈的直径  
                             System.out.println("光圈值: f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER) );  
                             System.out.println("曝光时间: " + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME)+ "秒" );  
                             System.out.println("ISO速度: " + directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT) );  
                             System.out.println("焦距: " + directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "毫米" );  
                               
                             System.out.println("拍照时间: " + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL) );  
                             System.out.println("宽: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH) );  
                             System.out.println("高: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT) );  
                               
                         }  
                           
                         if("ExifIFD0Directory".equalsIgnoreCase( directory.getClass().getSimpleName() )){  
                             System.out.println("照相机制造商: " + directory.getString(ExifIFD0Directory.TAG_MAKE) );  
                             System.out.println("照相机型号: " + directory.getString(ExifIFD0Directory.TAG_MODEL) );  
                             System.out.println("水平分辨率: " + directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION) );  
                             System.out.println("垂直分辨率: " + directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION) );  
                             System.out.println("TAG_ORIENTATION: " + directory.getString(ExifIFD0Directory.TAG_ORIENTATION));
                         }  
                     } 
            } catch (Exception e) {  
                e.printStackTrace();  
            } 
        }  
      
        public static void main(String[] args) {  
            metadataExtractor();  
        }  
    
}
