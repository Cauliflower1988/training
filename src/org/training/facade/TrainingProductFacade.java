/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

import org.training.data.TrainingProductDTO;


/**
 *
 */
public interface TrainingProductFacade
{

	List<TrainingProductDTO> getProductsForCatalogVersion(final CatalogVersionModel catalogVersion);

	TrainingProductDTO getProductForCode(final CatalogVersionModel catalogVersion, final String code);
}
