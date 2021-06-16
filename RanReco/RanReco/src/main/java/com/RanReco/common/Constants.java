package com.RanReco.common;

public class Constants {
	
	// 사용자 이미지 첨부파일을 저장할 경로
	private static final String UPLOAD_IMAGES_PATH = "C:/RanReco/MemberProfile";
	private static final String UPLOAD_LOCATIONS_PATH = "C:/RanReco/location";
	private static final String UPLOAD_FOODS_PATH = "C:/RanReco/food";
	private static final String UPLOAD_FOODS_INFO_PATH = "C:/RanReco/food_info";
	private static final String UPLOAD_FASHIONS_PATH = "C:/RanReco/fashion";
	private static final String UPLOAD_FASHION_INFO_PATH = "C:/RanReco/fashion_info";


	public static String getUploadProfilePath() {
		return UPLOAD_IMAGES_PATH;
	}
	
	public static String getUploadLocationPath() {
		return UPLOAD_LOCATIONS_PATH;
	}
	
	public static String getUploadFoodPath() {
		return UPLOAD_FOODS_PATH;
	}

	public static String getUploadFoodInfoPath() {
		return UPLOAD_FOODS_INFO_PATH;
	}
	
	public static String getUploadFashionPath() {
		return UPLOAD_FASHIONS_PATH;
	}
	public static String getUploadFashionInfoPath() {
		return UPLOAD_FASHION_INFO_PATH;
	}
}
