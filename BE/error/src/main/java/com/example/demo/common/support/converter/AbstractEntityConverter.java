package com.example.demo.common.support.converter;

import com.example.demo.common.persistence.BaseEntity;
import com.example.demo.common.support.AbstractModel;

public interface AbstractEntityConverter<T extends BaseEntity, R extends AbstractModel> {

    R from(T t);

    T toEntity(R t);
}