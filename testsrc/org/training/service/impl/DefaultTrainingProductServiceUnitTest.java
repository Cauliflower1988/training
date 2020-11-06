/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations.Mock;
import org.training.dao.TrainingProductDAO;



@UnitTest
public class DefaultTrainingProductServiceUnitTest
{
	private static final Logger LOG = Logger.getLogger(DefaultTrainingProductServiceUnitTest.class);
	private static final String PRODUCT_CODE = "PROD-001";

	private DefaultTrainingProductService trainingProductService;

	@Mock
	private TrainingProductDAO mockTrainingProductDAO;

	@Mock
	private ProductModel mockProductModel;

	@Mock
	private CatalogVersionModel mockCatalogVersion;


	@Before
	public void setUp() throws Exception
	{
		trainingProductService = new DefaultTrainingProductService();
		trainingProductService.setTrainingProductDAO(mockTrainingProductDAO);

	}

	@Test
	public void testReturnProductForGivenCode()
	{
		LOG.info("DefaultTrainingProductServiceUnitTest testReturnProductForGivenCode ====");
		//given
		when(mockTrainingProductDAO.findProductsByCode(PRODUCT_CODE)).thenReturn(Collections.singletonList(mockProductModel));
		//when
		final ProductModel productModel = trainingProductService.getProductForCode(PRODUCT_CODE);
		//then
		assertNotNull("No product found", productModel);
		//Assert.assertEquals(productModel, mockProductModel);

	}

	@Test
	public void testReturnsAllProductsForCatalogVersion()
	{
		//given
		when(mockTrainingProductDAO.findProductsByCatalogVersion(mockCatalogVersion))
				.thenReturn(Collections.singletonList(mockProductModel));
		//when
		final List<ProductModel> productModels = trainingProductService.getProductsForCatalogVersion(mockCatalogVersion);
		//then
		assertThat(productModels).hasSize(1);
	}


}
