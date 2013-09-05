package sk.garwan.hibernate2pc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	@Override
	@Transactional
	public void doInTransaction() {
		authorService.createAuthor("author 1");
		bookService.createBook("book 1");
	}

	@Override
	@Transactional
	public void doInTransactionWithException() {
		authorService.createAuthor("author 2");
		bookService.createBook("book with a long name");
	}

	@Override
	public void doWithoutTransactionWithException() {
		authorService.createAuthor("author 2");
		bookService.createBook("book with a long name");
	}
}
