/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 *
 */
public interface TrainingProductService
{
	ProductModel getProductForCode(final CatalogVersionModel catalogVersion, final String code);

	List<ProductModel> getProductsForCatalogVersion(final CatalogVersionModel catalogVersion);
}
