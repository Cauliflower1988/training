package org.training.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.Assert;


public class DefaultProductDAOIntegrationTest extends ServicelayerTransactionalTest
{

	private static final Logger LOG = LoggerFactory.getLogger(DefaultProductDAOIntegrationTest.class);
	@Resource
	private TrainingProductDAO trainingProductDAO;

	@Resource
	private ModelService modelService;

	@Resource
	protected CatalogVersionService catalogVersionService;


	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
	}

	@Test
	public void testFindProductByCode()
	{
		LOG.info("testFindProductByCode..");
		final List<ProductModel> products = trainingProductDAO.findProductsByCode("testProduct0");
		assertThat(products).isNotNull();
		assertThat(products).isNotEmpty();
		assertEquals("Code", "testProduct0", products.get(0).getCode());
	}


	@Test
	public void testFindProductWhenCodeIsNull() throws Exception
	{
		LOG.info("testFindProductWhenCodeIsNull..");
		//when
		try
		{
			trainingProductDAO.findProductsByCode(null);
			Assert.fail("Should throw IllegalArgumentException when code is null");
		}
		catch (final IllegalArgumentException ex)
		{
			//OK
		}
	}

	@Test
	public void testFindProductWhenCodeIsUnknown() throws Exception
	{
		LOG.info("testFindProductWhenCodeIsUnknown..");
		//when
		final List<ProductModel> products = trainingProductDAO.findProductsByCode("unknown");

		//then
		assertThat(products).isNotNull();
		assertThat(products).isEmpty();
	}

	@Test
	public void testFindProductsFromCatalogVersion()
	{
		LOG.info("testFindProductsFromCatalogVersion..");
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final List<ProductModel> allProducts = trainingProductDAO.findProductsByCatalogVersion(catalogVersion);
		assertThat(allProducts).overridingErrorMessage("should be 10 products for a catalog version").hasSize(10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindProductsFromCatalogVersionWhenNull()
	{
		LOG.info("testFindProductsFromCatalogVersionWhenNull..");

		trainingProductDAO.findProductsByCatalogVersion(null);
	}


}
