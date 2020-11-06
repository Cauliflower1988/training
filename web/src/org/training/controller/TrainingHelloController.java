/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.controller;

import static org.training.constants.TrainingConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.service.TrainingService;
import org.training.service.impl.DefaultTrainingService;
import org.training.web.ProductDTO;


@Controller
@RequestMapping("/hello")
public class TrainingHelloController
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultTrainingService.class);
	@Autowired
	private TrainingService trainingService;
	@Resource
	private FlexibleSearchService flexibleSearchService;
	@Autowired
	private CatalogVersionService catalogVersionService;


	@Resource
	private ProductService productService;




	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		//model.addAttribute("logoUrl", trainingService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "index";
	}


	//	@RequestMapping(value = "/{baseSiteId}/products}", method = RequestMethod.GET)
	//	public String printProduct(@PathVariable final String baseSiteId, @PathVariable final String productCode,
	//			@RequestParam(required = true) final String storeName)
	//	{
	//
	//		model.addAttribute("logoUrl", trainingService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
	//		return "welcome";
	//	}


	@RequestMapping(value = "/sub1", method = RequestMethod.GET)
	public String printSub1(final ModelMap model)
	{
		LOG.debug("sub1 ");
		model.addAttribute("logoUrl", trainingService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "sub1";
	}

	@RequestMapping(value = "/sub2", method = RequestMethod.GET)
	public String printSub2()
	{
		LOG.debug("sub2 ");
		return "sub2";
	}



	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductDTO> printProducts(final ModelMap model)
	{
		LOG.info("get products1");
		final CatalogVersionModel catVersion = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
		catalogVersionService.setSessionCatalogVersions(Collections.singleton(catVersion));
		final String query = "SELECT {pk} FROM {Product}";
		final List<ProductModel> productModels = flexibleSearchService.<ProductModel> search(query).getResult();
		LOG.info(" products size: " + productModels.size());

		final List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (final ProductModel pm : productModels)
		{
			final ProductDTO pd = new ProductDTO();
			pd.setId(pm.getPk().toString());
			pd.setCode(pm.getCode());
			pd.setName(pm.getName());
			pd.setDescription(pm.getDescription());
			pd.setOnlineDate(pm.getOnlineDate());
			pd.setOnlineDate(pm.getOfflineDate());
			productDTOs.add(pd);
		}
		model.addAttribute("productDTOs", productDTOs);
		return productDTOs;
	}


	@RequestMapping(value = "/products1", method = RequestMethod.GET)
	public String printProducts1(final ModelMap model)
	{
		LOG.info("get products");
		final CatalogVersionModel catVersion = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
		catalogVersionService.setSessionCatalogVersions(Collections.singleton(catVersion));
		final String query = "SELECT {pk},{code},{name},{description},{onlineDate},{OfflineDate} FROM {Product}";
		final List<ProductModel> productModels = flexibleSearchService.<ProductModel> search(query).getResult();
		LOG.info(" products size: " + productModels.size());
		model.addAttribute("productModelList", productModels);
		return "products1";
	}

	//can work with jsp directly
	@RequestMapping(value = "/products2", method = RequestMethod.GET)
	public String printProducts2(final ModelMap model)
	{
		LOG.info("get products2");
		final CatalogVersionModel catVersion = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
		catalogVersionService.setSessionCatalogVersions(Collections.singleton(catVersion));
		final String query = "SELECT {pk} FROM {Product}";
		final List<ProductModel> productModels = flexibleSearchService.<ProductModel> search(query).getResult();
		LOG.info(" products size: " + productModels.size());

		final List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (final ProductModel pm : productModels)
		{
			final ProductDTO pd = new ProductDTO();
			pd.setId(pm.getPk().toString());
			pd.setCode(pm.getCode());
			pd.setName(pm.getName());
			pd.setDescription(pm.getDescription());
			pd.setOnlineDate(pm.getOnlineDate());
			pd.setOnlineDate(pm.getOfflineDate());
			productDTOs.add(pd);
		}
		model.addAttribute("productDTOs", productDTOs);
		return "products";

	}


	//	@RequestMapping(value = "/products2", method = RequestMethod.GET)
	//	public String printProducts2(final ModelMap model)
	//	{
	//		LOG.info("get products1");
	//		final CatalogVersionModel catVersion = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
	//		catalogVersionService.setSessionCatalogVersions(Collections.singleton(catVersion));
	//		final List<ProductModel> productModels = productService.getAllProductsForCatalogVersion(catVersion);
	//
	//
	//		final List<TrainingProductDTO> productDTOs = new ArrayList<TrainingProductDTO>();
	//		for (final ProductModel pm : productModels)
	//		{
	//			final TrainingProductDTO pd = new TrainingProductDTO();
	//			pd.setId(pm.getPk().toString());
	//			pd.setCode(pm.getCode());
	//			pd.setDescription(pm.getDescription());
	//			productDTOs.add(pd);
	//		}
	//		model.addAttribute("productDTOs", productDTOs);
	//		return "products2";
	//	}

	//	@RequestMapping(value = "/products3", method = RequestMethod.GET)
	//	public String printProducts3(final ModelMap model)
	//	{
	//		LOG.info("get products3");
	//		final CatalogVersionModel catVersion = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
	//		catalogVersionService.setSessionCatalogVersions(Collections.singleton(catVersion));
	//		final String query = "SELECT {pk} FROM {Product}";
	//		final List<ProductModel> productModels = productDao.findProductsByCatalogVersion(catalogVersion);
	//
	//
	//		LOG.info(" products size: " + productModels.size());
	//
	//		final org.training.data.ProductPojo
	//
	//		model.addAttribute("productModelList", productModels);
	//		return "products";
	//	}


}
