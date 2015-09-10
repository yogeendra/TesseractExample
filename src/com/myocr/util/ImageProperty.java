package com.myocr.util;

public class ImageProperty {
	private int width;
	private int height;
	private String format;
	private String geometry;
	private int depth;
	private String imageClass;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getImageClass() {
		return imageClass;
	}
	public void setImageClass(String imageClass) {
		this.imageClass = imageClass;
	}
	@Override
	public String toString() {
		return "ImageProperty [width=" + width + ", height=" + height
				+ ", format=" + format + ", geometry=" + geometry + ", depth="
				+ depth + ", imageClass=" + imageClass + "]";
	}
	
	
}
