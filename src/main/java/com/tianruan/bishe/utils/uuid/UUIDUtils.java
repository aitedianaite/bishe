package com.tianruan.bishe.utils.uuid;


import java.util.Random;

public class UUIDUtils {

	public static String randomUUID10() {
		return RandomUtils.randomString(10);
	}
	
	public static String randomUUID20() {
		return RandomUtils.randomString(20);
	}
	
	public static String randomUUID(int length) {
		return RandomUtils.randomString(length);
	}
	
	public static String getUUIDPath(String uuid){
		StringBuilder builder=new StringBuilder();
		builder.append("/");
		builder.append((uuid.substring(0, 3).hashCode())%100+"").append("/");
		builder.append((uuid.substring(7,10).hashCode())%100+"").append("/");
		builder.append((uuid.substring(11,14).hashCode())%100+"").append("/");
		return builder.toString();
	}
	
	public static String getAttTable(){
		Random rand = new Random();
		int nextInt = rand.nextInt(10)+1;
		StringBuilder builder=new StringBuilder();
		builder.append("ATT_FILE_").append(String.format("%02d", nextInt));
		return builder.toString();
	}

	public static Long randomUUIDInt10() {
		String str = new String();
		Long long1=null;
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int i1 = random.nextInt(10);
			str = str+i1;
		}
		Long aLong = Long.valueOf(str);

		return aLong;
	}

	public static Long randomUUIDInt8() {
		String str = new String();
		Long long1=null;
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			int i1 = random.nextInt(8);
			str = str+i1;
		}
		Long aLong = Long.valueOf(str);

		return aLong;
	}
	public static String randomUUIDInt13() {
		String str = new String();
		Random random = new Random();
		for (int i = 0; i < 13; i++) {
			int i1 = random.nextInt(13);
			str = str+i1;
		}
		return str;
	}
	
}
