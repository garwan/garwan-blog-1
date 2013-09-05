package sk.garwan.hibernate2pc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sk.garwan.hibernate2pc.dao.AuthorDao;
import sk.garwan.hibernate2pc.domain.Author;


@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDao authorDao;
	
	@Override
	@Transactional
	public Author createAuthor(String name) {
		Author author = new Author();
		author.setName(name);
		author = authorDao.saveAuthor(author);
		return author;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Author> loadAllAuthors() {
		return authorDao.loadAllAuthors();
	}
}
