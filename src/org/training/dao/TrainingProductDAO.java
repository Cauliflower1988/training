/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 *
 */
public interface TrainingProductDAO
{
	List<ProductModel> findProductsByCode(final CatalogVersionModel catalogVersion, final String code);

	List<ProductModel> findProductsByCatalogVersion(final CatalogVersionModel catalogVersion);

	ProductModel findProductByPK(String pk);
}
