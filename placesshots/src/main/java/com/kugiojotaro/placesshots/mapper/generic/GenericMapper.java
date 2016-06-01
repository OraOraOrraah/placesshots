package com.kugiojotaro.placesshots.mapper.generic;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericMapper<T extends Serializable, R extends Serializable> {
	
	T toDtoBean(R r);
	
	List<T> toDtoBean(List<? extends R> r);
	
	Page<T> toDtoBean(Page<? extends R> r, Pageable pageable);
	
	R toPersistenceBean(T t);
	
	R toPersistenceBean(T t, R r);
	
	List<R> toPersistenceBean(List<? extends T> t);
	
}