package sk.garwan.hibernate2pc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sk.garwan.hibernate2pc.dao.BookDao;
import sk.garwan.hibernate2pc.domain.Book;


@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	@Transactional
	public Book createBook(String name) {
		Book book = new Book();
		book.setName(name);
		book = bookDao.saveBook(book);
		return book;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> loadAllBooks() {
		return bookDao.loadAllBooks();
	}
}
