package com.itwill.jpa.util;

import net.bytebuddy.implementation.bind.annotation.Super;

public class CustomJWTException extends RuntimeException {
	public CustomJWTException(String msg) {
		super(msg);
	}
}

