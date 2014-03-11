package com.sky.dvdstore;

public class DvdException extends Exception
{

	private DvdErrorCode	code	= null;

	public DvdException(DvdErrorCode errorCode)
	{
		this.code = errorCode;

	}

	@Override
	public String getMessage()
	{

		return code.getMessage();
	}

}
