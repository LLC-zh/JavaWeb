package com.web.util;

import java.util.UUID;

public class UUidUtil {
	
	public static String getUUid() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

}
