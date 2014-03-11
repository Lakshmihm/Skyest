package com.sky.dvdstore.impl;

import com.sky.dvdstore.Dvd;
import com.sky.dvdstore.DvdErrorCode;
import com.sky.dvdstore.DvdException;
import com.sky.dvdstore.DvdRepository;
import com.sky.dvdstore.DvdService;

public class DvdServiceImpl implements DvdService
{

	private DvdRepository		dvdRepository	= null;
	private String				prefix			= null;

	private static final int	WORD_LIMIT		= 10;

	private static final String	WORD_SEPARATOR	= " ";

	public DvdServiceImpl(DvdRepository repository, String prefix)
	{
		this.dvdRepository = repository;
		this.prefix = prefix;
	}

	@Override
	public Dvd retrieveDvd(String dvdReference) throws DvdException
	{

		checkInvalidReference(dvdReference);

		return getDvd(dvdReference);

	}

	private Dvd getDvd(String dvdReference) throws DvdException
	{
		Dvd dvd = dvdRepository.retrieveDvd(dvdReference);

		if (dvd == null)
		{
			throw new DvdException(DvdErrorCode.DVD_NOT_FOUND);
		}
		return dvd;
	}

	private void checkInvalidReference(String dvdReference) throws DvdException
	{
		if (dvdReference != null && !dvdReference.startsWith(prefix))
		{

			throw new DvdException(DvdErrorCode.INVALID_TEXT);
		}
	}

	@Override
	public String getDvdSummary(String dvdReference) throws DvdException
	{

		checkInvalidReference(dvdReference);

		Dvd dvd = getDvd(dvdReference);

		return format(dvd);
	}

	private String format(Dvd dvd)
	{

		StringBuilder builder = new StringBuilder();
		builder.append("[").append(dvd.getReference()).append("] ").append(dvd.getTitle()).append(" - ");

		String[] review = dvd.getReview().split(WORD_SEPARATOR, WORD_LIMIT + 1);
		int limit = Math.min(WORD_LIMIT, review.length);

		for (int i = 0; i < limit; i++)
		{
			builder.append(review[i]);
			if (i != limit - 1) builder.append(WORD_SEPARATOR);
		}

		if (review.length > WORD_LIMIT) builder.append("...");

		return builder.toString();
	}

	/*
	 * This is the format implementation using Guava library's Joiner and Splitter. Commented as not sure if the usage of other libraries is allowed
	 */
	//	private String format(Dvd dvd)
	//	{
	//
	//		Joiner joiner = Joiner.on(WORD_SEPARATOR).skipNulls();
	//		List<String> review = Lists.newArrayList(Splitter.on(WORD_SEPARATOR).limit(WORD_LIMIT + 1).split(dvd.getReview()));
	//		int limit = Math.min(WORD_LIMIT, review.size());
	//
	//		StringBuilder builder = new StringBuilder();
	//		builder.append("[").append(dvd.getReference()).append("] ");
	//		joiner.appendTo(builder,dvd.getTitle(),"- ");
	//		joiner.appendTo(builder, review.subList(0, limit));
	//
	//		if (review.size() > WORD_LIMIT) builder.append("...");
	//		return builder.toString();
	//
	//	}

}
