package com.kugiojotaro.placesshots.mapper.generic;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class GenericMapperImpl<T extends Serializable, R extends Serializable> implements GenericMapper<T, R> {

	private static final Logger LOGGER = Logger.getLogger(GenericMapperImpl.class);
	
	@Override
	public T toDtoBean(R r) {
		@SuppressWarnings("unchecked")
		Class<T> cl = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		T dto = null;
		
		try {
			dto = cl.newInstance();
			ConvertUtils.register(new DateConverter(null), Date.class);
			BeanUtils.copyProperties(dto, r);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e, e);
		}

		return dto;
	}
	
	@Override
	public List<T> toDtoBean(List<? extends R> r) {
		List<T> list = new ArrayList<T>();
		for (R item : r) {
			list.add(toDtoBean(item));
		}
		return list;
	}

	@Override
	public Page<T> toDtoBean(Page<? extends R> r, Pageable pageable) {
		Page<T> pageDto = new PageImpl<T>(toDtoBean(r.getContent()), pageable, r.getTotalElements());
		return pageDto;

	}

	@Override
	public R toPersistenceBean(T dto) {
		return toPersistenceBean(dto, null);
	}

	@Override
	public List<R> toPersistenceBean(List<? extends T> t) {
		List<R> list = new ArrayList<R>();
		for (T item : t) {
			list.add(toPersistenceBean(item));
		}
		return list;
	}

	@Override
	public R toPersistenceBean(T dto, R entity) {
		@SuppressWarnings("unchecked")
		Class<R> cl = (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		R persist = null;

		if (dto != null) {
			try {
				if (entity == null) {
					persist = cl.newInstance();
				} else {
					persist = entity;
				}
				ConvertUtils.register(new DateConverter(null), Date.class);
				BeanUtils.copyProperties(persist, dto);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				LOGGER.error(e, e);
			}
		}

		return persist;
	}

}