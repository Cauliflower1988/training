/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.dao.TrainingProductDAO;


/**
 *
 */
@Component(value = "trainingProductDAO")
public class DefaultTrainingProductDAO implements TrainingProductDAO
{
	@Autowired
	private ProductDao productDao;



	@Override
	public List<ProductModel> findProductsByCode(final CatalogVersionModel catalogVersion, final String code)
	{
		return productDao.findProductsByCode(catalogVersion, code);
	}

	@Override
	public List<ProductModel> findProductsByCatalogVersion(final CatalogVersionModel catalogVersion)
	{
		return productDao.findProductsByCatalogVersion(catalogVersion);
	}


	@Override
	public ProductModel findProductByPK(final String pk)
	{
		// XXX Auto-generated method stub
		return null;
	}





}
