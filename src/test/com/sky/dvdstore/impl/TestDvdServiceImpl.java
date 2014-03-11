package com.sky.dvdstore.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sky.dvdstore.Dvd;
import com.sky.dvdstore.DvdErrorCode;
import com.sky.dvdstore.DvdException;
import com.sky.dvdstore.DvdRepositoryStub;
import com.sky.dvdstore.DvdService;

public class TestDvdServiceImpl
{

	DvdService					service	= null;
	@Rule
	public ExpectedException	thrown	= ExpectedException.none();

	@Before
	public void setUp() throws Exception
	{
		service = new DvdServiceImpl(new DvdRepositoryStub(), DvdRepositoryStub.DVD_REFERENCE_PREFIX);
	}

	@Test
	public void retrieveDvdInvalidText() throws DvdException
	{
		thrown.expect(DvdException.class);
		thrown.expectMessage(DvdErrorCode.INVALID_TEXT.getMessage());

		service.retrieveDvd("INVALID-TEXT");
	}

	@Test
	public void retrieveValidDvd() throws DvdException
	{

		Dvd dvd = service.retrieveDvd("DVD-TG423");
		assertEquals("Topgun", dvd.getTitle());
	}

	@Test
	public void retrieveDvdNotFound() throws DvdException
	{
		thrown.expect(DvdException.class);
		thrown.expectMessage(DvdErrorCode.DVD_NOT_FOUND.getMessage());
		service.retrieveDvd("DVD-999");
	}

	@Test
	public void dvdSummaryInvalidText() throws DvdException
	{
		thrown.expect(DvdException.class);
		thrown.expectMessage(DvdErrorCode.INVALID_TEXT.getMessage());

		service.getDvdSummary("INVALID-TEXT");
	}

	@Test
	public void dvdSummaryNotFound() throws DvdException
	{
		thrown.expect(DvdException.class);
		thrown.expectMessage(DvdErrorCode.DVD_NOT_FOUND.getMessage());
		service.getDvdSummary("DVD-999");
	}

	@Test
	public void dvdSummaryValidText() throws DvdException
	{
		String summary = service.getDvdSummary("DVD-TG423");
		assertEquals("[DVD-TG423] Topgun - All action film", summary);
	}

	@Test
	public void dvdSummaryFirstTenWords() throws DvdException
	{
		String summary = service.getDvdSummary("DVD-S765");
		assertEquals("[DVD-S765] Shrek - Big green monsters, they're just all the rage these days,...", summary);
	}
	
	@Test
	public void dvdSummaryExactTenWords() throws DvdException{
		String summary = service.getDvdSummary("DVD-C1234");
		assertEquals("[DVD-C1234] Life is Beautiful - A deeply moving blend of cold terror and rapturous hilarity", summary);
	}

}
