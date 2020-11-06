/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.impl;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.data.TrainingProductDTO;
import org.training.facade.impl.DefaultTrainingProductFacade;


public class DefaultTrainingProductFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultTrainingProductFacadeIntegrationTest.class);

	@Resource
	private DefaultTrainingProductFacade trainingProductFacade;
	@Resource
	private ModelService modelService;
	@Resource
	protected CatalogVersionService catalogVersionService;


	private static final String TEST_PRODUCT_CODE = "testProduct0";

	@Before
	public void setUp() throws Exception
	{
		LOG.info("Creating data for product facade..");
		final long startTime = System.currentTimeMillis();
		createCoreData();
		createDefaultCatalog();
		LOG.info("Finished data for product facade " + (System.currentTimeMillis() - startTime) + "ms");

	}

	@Test
	public void testGetProductForCode()
	{
		LOG.info("DefaultTrainingProductFacadeIntegrationTest testGetProductForCode..");
		final TrainingProductDTO TrainingProductDTO = trainingProductFacade.getProductForCode(TEST_PRODUCT_CODE);
		Assert.assertNotNull(TrainingProductDTO);
		Assert.assertEquals(TEST_PRODUCT_CODE, TrainingProductDTO.getCode());
	}

	@Test
	public void testGetProductForCatalogVersion()
	{
		LOG.info("DefaultTrainingProductFacadeIntegrationTest testGetProductForCatalogVersion..");
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final List<TrainingProductDTO> TrainingProductDTOs = trainingProductFacade.getProductsForCatalogVersion(catalogVersion);
		Assert.assertNotNull(TrainingProductDTOs);
		assertThat(TrainingProductDTOs).overridingErrorMessage("should be 10 products for a catalog version").hasSize(10);
	}

}
