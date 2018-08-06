package com.book.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.book.api.model.BookDTO;
import com.book.domain.Book;

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	BookDTO bookToBookDTO(Book book);
}
