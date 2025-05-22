package com.store.book.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.store.book.request.model.BookInfo;
import com.store.book.service.model.Book;

@Mapper(componentModel = "spring")
public interface ResponseObjectMapper {

	List<BookInfo> toBookInfoResponse(List<Book> books);

}
