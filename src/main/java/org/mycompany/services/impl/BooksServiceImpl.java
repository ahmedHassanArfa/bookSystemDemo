package org.mycompany.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.mycompany.common.Book;
import org.mycompany.dao.BooksDao;
import org.mycompany.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {
	
	@Autowired
	private BooksDao booksDao;

	public void save(Book book) throws Exception {
		booksDao.persist(book);
		
	}

	public void update(Book book) throws Exception {
		booksDao.merge(book);
		
	}

	public void delete(Long id) throws Exception {
		booksDao.remove(id);
		
	}

	public Book findById(Long id) throws Exception {
		return booksDao.findById(id);
	}

	public List<Book> findAll() throws Exception {
		return booksDao.findAll();
	}
	
	

}
