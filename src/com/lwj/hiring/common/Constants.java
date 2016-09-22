package com.lwj.hiring.common;

import com.lwj.hiring.common.util.DBUtil;



public class Constants {
	private Constants() {

	}

	public static final String ENCODE_UTF8 = "UTF-8";
	public static final String DB_DRIVER = DBUtil.getDefaultBundle("driver");
	public static final String DB_URL = DBUtil.getDefaultBundle("url");
	public static final String DB_USERNAME=DBUtil.getDefaultBundle("username");
	public static final String DB_PASSWORD = DBUtil.getDefaultBundle("password");
}
