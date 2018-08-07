package cn.inbs.blockchain;

import org.apache.commons.lang.StringUtils;

import cn.inbs.blockchain.common.third.FileUpload2AliYun;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = FileUpload2AliYun.getURL();
		int length = StringUtils.length(url);
		System.out.println(url);
		System.out.println(length);
	}

}
