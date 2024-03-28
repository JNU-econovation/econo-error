package com.example.demo.common.support.converter;

import com.example.demo.common.support.AbstractModel;
import com.example.demo.common.support.dto.AbstractDto;

public interface AbstractDtoConverter<T extends AbstractDto, R extends AbstractModel> {
    R from(T t);
}