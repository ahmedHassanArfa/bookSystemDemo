package org.mycompany.services;

import java.util.List;

import org.mycompany.common.Book;

public interface BooksService {
	
	void save(Book book) throws Exception;
	
	void update(Book book) throws Exception;
	
	void delete(Long id) throws Exception;
	
	Book findById(Long id) throws Exception;

	List<Book> findAll() throws Exception;
	
}
