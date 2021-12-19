/*
 * 
 */
package com.example.consumerBank.java.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Class BaseService.
 *
 * @param <D> the generic type
 * @param <E> the element type
 *
 *@author Radu
 */
public class BaseService<D, E> {
	
	/** The dto class. */
	private final Class<D> dtoClass;

	/** The entity class. */
	private final Class<E> entityClass;

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Instantiates a new base service.
	 */
	@SuppressWarnings("unchecked")
	public BaseService() {
		dtoClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	/**
	 * To dtos.
	 *
	 * @param entities the entities
	 * @return the list
	 */
	protected List<D> toDtos(List<E> entities) {
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}

	/**
	 * To entities.
	 *
	 * @param dtos the dtos
	 * @return the list
	 */
	protected List<E> toEntities(List<D> dtos) {
		return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	}

	/**
	 * To dto.
	 *
	 * @param entity the entity
	 * @return the d
	 */
	protected D toDto(E entity) {
		return modelMapper.map(entity, dtoClass);
	}

	/**
	 * To entity.
	 *
	 * @param dto the dto
	 * @return the e
	 */
	protected E toEntity(D dto) {
		return modelMapper.map(dto, entityClass);
	}
}
