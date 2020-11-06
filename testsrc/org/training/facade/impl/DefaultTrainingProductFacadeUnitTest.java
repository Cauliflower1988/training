/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations.Mock;
import org.training.service.TrainingProductService;

import junit.framework.Assert;


/**
 *
 */
public class DefaultTrainingProductFacadeUnitTest
{
	private static final Logger LOG = Logger.getLogger(DefaultTrainingProductFacadeUnitTest.class);

	private static final String PRODUCT_CODE = "PROD-001";

	@InjectMocks
	private final DefaultTrainingProductFacade trainingProductFacade = new DefaultTrainingProductFacade();

	@Mock
	private TrainingProductService mockTrainingProductService;

	@Mock
	private ProductModel mockProductModel;

	@Mock
	private CatalogVersionModel mockCatalogVersion;

	@Before
	public void setUp() throws Exception
	{
		trainingProductFacade.setTrainingProductService(mockTrainingProductService);
	}

	@Test
	public void testGetProductForGivenCode()
	{
		LOG.info("DefaultTrainingProductServiceUnitTest testReturnProductForGivenCode ====");
		//given
		when(mockTrainingProductService.getProductForCode(PRODUCT_CODE)).thenReturn(mockProductModel);
		//when
		final ProductModel productModel = mockTrainingProductService.getProductForCode(PRODUCT_CODE);
		//then
		assertNotNull("No product found", productModel);
		Assert.assertEquals(productModel, mockProductModel);

	}

	@Test
	public void testGetProductForGivenCatalogVersion()
	{
		LOG.info("DefaultTrainingProductServiceUnitTest testReturnProductForGivenCode ====");

		//given
		when(mockTrainingProductService.getProductsForCatalogVersion(mockCatalogVersion))
				.thenReturn(Collections.singletonList(mockProductModel));
		//when
		final List<ProductModel> productModels = mockTrainingProductService.getProductsForCatalogVersion(mockCatalogVersion);
		//then

		Assert.assertNotNull(productModels);
		assertThat(productModels).hasSize(1);
		assertThat(Integer.valueOf(productModels.size()), equalTo(Integer.valueOf(1)));


	}

}
