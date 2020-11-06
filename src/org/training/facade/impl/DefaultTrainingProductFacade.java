/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.training.data.TrainingProductDTO;
import org.training.facade.TrainingProductFacade;
import org.training.service.TrainingProductService;


/**
 *
 */
public class DefaultTrainingProductFacade implements TrainingProductFacade
{
	@Resource
	private TrainingProductService trainingProductService;

	@Resource
	private CatalogVersionService catalogVersionService;


	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @return the trainingProductService
	 */
	public TrainingProductService getTrainingProductService()
	{
		return trainingProductService;
	}

	/**
	 * @param trainingProductService
	 *           the trainingProductService to set
	 */
	public void setTrainingProductService(final TrainingProductService trainingProductService)
	{
		this.trainingProductService = trainingProductService;
	}

	@Override
	public List<TrainingProductDTO> getProductsForCatalogVersion(final CatalogVersionModel catalogVersion)
	{
		catalogVersionService.setSessionCatalogVersions(Collections.singleton(catalogVersion));
		final List<ProductModel> productModels = trainingProductService.getProductsForCatalogVersion(catalogVersion);
		final List<TrainingProductDTO> trainingProductDTOs = new ArrayList<TrainingProductDTO>();
		for (final ProductModel pm : productModels)
		{
			final TrainingProductDTO pd = new TrainingProductDTO();
			pd.setId(pm.getPk().toString());
			pd.setCode(pm.getCode());
			pd.setName(pm.getName());
			pd.setDescription(pm.getDescription());
			trainingProductDTOs.add(pd);
		}
		return trainingProductDTOs;

	}

	@Override
	public TrainingProductDTO getProductForCode(final CatalogVersionModel catalogVersion, final String code)
	{
		final ProductModel productModel = trainingProductService.getProductForCode(catalogVersion, code);
		final TrainingProductDTO trainingProductDTO = new TrainingProductDTO();
		trainingProductDTO.setId(productModel.getPk().toString());
		trainingProductDTO.setCode(productModel.getCode());
		trainingProductDTO.setName(productModel.getName());
		trainingProductDTO.setDescription(productModel.getDescription());
		return trainingProductDTO;
	}

}
