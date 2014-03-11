package com.sky.dvdstore;

public enum DvdErrorCode
{

	DVD_NOT_FOUND("DVD not found"), INVALID_TEXT("DVD reference must begin with DVD-");

	private String	message	= null;

	private DvdErrorCode(String code)
	{
		this.message = code;
	}

	public String getMessage()
	{
		return message;
	}

}
