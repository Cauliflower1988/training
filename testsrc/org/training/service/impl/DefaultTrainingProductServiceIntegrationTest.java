/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.training.service.TrainingProductService;


/**
 *
 */

public class DefaultTrainingProductServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(DefaultTrainingProductServiceIntegrationTest.class);
	@Resource
	private TrainingProductService trainingProductService;
	@Resource
	protected CatalogVersionService catalogVersionService;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
	}

	@Test
	public void testGetProductForCode()
	{
		LOG.info("DefaultTrainingProductServiceIntegrationTest testGetProductForCode ====");
		final String code = "testProduct0";
		final ProductModel productModel = trainingProductService.getProductForCode(code);
		assertNotNull("No product found", productModel);

	}

	@Test
	public void testGetProductsForCatalogVersion()
	{

		LOG.info("DefaultTrainingProductServiceIntegrationTest testGetProductsForCatalogVersion ====");
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		LOG.info("DefaultTrainingProductServiceIntegrationTest testGetProductsForCatalogVersion catalogVersion ===="
				+ catalogVersion.toString());
		final List<ProductModel> allProducts = trainingProductService.getProductsForCatalogVersion(catalogVersion);
		assertThat(allProducts).overridingErrorMessage("should be 10 products for a catalog version").hasSize(10);

	}


}
