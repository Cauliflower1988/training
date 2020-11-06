/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.web;

import java.util.Date;


/**
 *
 */
public class ProductDTO
{
	private String id;
	private String code;
	private String name;
	private String description;
	private Date onlineDate;
	private Date offlineDate;

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *           the id to set
	 */
	public void setId(final String id)
	{
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *           the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *           the description to set
	 */
	public void setDescription(final String description)
	{
		this.description = description;
	}

	/**
	 * @return the onlineDate
	 */
	public Date getOnlineDate()
	{
		return onlineDate;
	}

	/**
	 * @param onlineDate
	 *           the onlineDate to set
	 */
	public void setOnlineDate(final Date onlineDate)
	{
		this.onlineDate = onlineDate;
	}

	/**
	 * @return the offlineDate
	 */
	public Date getOfflineDate()
	{
		return offlineDate;
	}

	/**
	 * @param offlineDate
	 *           the offlineDate to set
	 */
	public void setOfflineDate(final Date offlineDate)
	{
		this.offlineDate = offlineDate;
	}

	@Override
	public String toString()
	{
		return "ProductDTO [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", onlineDate="
				+ onlineDate + ", offlineDate=" + offlineDate + "]";
	}
}
