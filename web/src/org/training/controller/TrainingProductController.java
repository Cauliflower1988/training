/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.data.TrainingProductDTO;
import org.training.facade.TrainingProductFacade;
import org.training.service.impl.DefaultTrainingService;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
public class TrainingProductController
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultTrainingService.class);
	@Autowired
	private TrainingProductFacade trainingProductFacade;
	@Autowired
	private CatalogVersionService catalogVersionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printTrainingHome(final ModelMap model)
	{
		//model.addAttribute("logoUrl", trainingService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "index";
	}

	@RequestMapping(value = "/sub1", method = RequestMethod.GET)
	public String printSub1(final ModelMap model)
	{
		LOG.debug("sub1 ");
		model.addAttribute("logoUrl");
		return "sub1";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		LOG.debug("sub1 ");
		model.addAttribute("logoUrl", "");
		return "welcome";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ResponseBody
	public List<TrainingProductDTO> getProductsForCatalogVersion(final ModelMap model)
	{
		LOG.info("get products");
		final CatalogVersionModel catVersion = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
		final List<TrainingProductDTO> productDTOs = trainingProductFacade.getProductsForCatalogVersion(catVersion);
		LOG.info(" products size: " + productDTOs.size());
		model.addAttribute("productDTOs", productDTOs);
		return productDTOs;
	}

	@RequestMapping(value = "/product/{productCode}", method = RequestMethod.GET)
	@ResponseBody
	public TrainingProductDTO getProductForCode(@PathVariable("productCode")
	final String productCode, final ModelMap model)
	{
		LOG.info("get product");
		final CatalogVersionModel catVersion = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
		catalogVersionService.setSessionCatalogVersions(Collections.singleton(catVersion));
		final TrainingProductDTO productDTO = trainingProductFacade.getProductForCode(catVersion, productCode);
		LOG.info("get product: " + productDTO.toString());
		model.addAttribute("productDTO", productDTO);
		return productDTO;
	}
}
