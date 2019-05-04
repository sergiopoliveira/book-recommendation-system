package com.book.api.mapper;

import com.book.api.model.BookDTO;
import com.book.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	BookDTO bookToBookDTO(Book book);

	Book bookDTOToBook(BookDTO bookDTO);
}
