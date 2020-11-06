/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import org.apache.log4j.Logger;
import org.training.dao.TrainingProductDAO;
import org.training.service.TrainingProductService;


public class DefaultTrainingProductService implements TrainingProductService
{
	private static final Logger LOG = Logger.getLogger(DefaultTrainingProductService.class);

	private TrainingProductDAO trainingProductDAO;


	public TrainingProductDAO getTrainingProductDAO()
	{
		return trainingProductDAO;
	}


	public void setTrainingProductDAO(final TrainingProductDAO trainingProductDAO)
	{
		this.trainingProductDAO = trainingProductDAO;
	}

	@Override
	public ProductModel getProductForCode(final CatalogVersionModel catalogVersion, final String code)
	{
		final List<ProductModel> result = trainingProductDAO.findProductsByCode(catalogVersion, code);
		if (result.isEmpty())
		{
			throw new UnknownIdentifierException("product with code" + code + " not found");
		}
		else if (result.size() > 1)
		{
			throw new AmbiguousIdentifierException("product code " + code + " is not unique, " + result.size() + " statiums found!");
		}
		return result.get(0);

	}

	@Override
	public List<ProductModel> getProductsForCatalogVersion(final CatalogVersionModel catalogVersion)
	{
		return trainingProductDAO.findProductsByCatalogVersion(catalogVersion);
	}

}
