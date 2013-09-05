package sk.garwan.hibernate2pc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sk.garwan.hibernate2pc.domain.Author;


@Repository
public class AuthorDao {

	@Qualifier("authorSessionFactory")
	@Autowired
	private SessionFactory sf;
	
	@Transactional(propagation = Propagation.MANDATORY)
	public Author saveAuthor(Author author) {
		Session session = sf.getCurrentSession();
		session.save(author);
		return author;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.MANDATORY)
	public List<Author> loadAllAuthors() {
		Criteria crit = sf.getCurrentSession().createCriteria(Author.class);
		return crit.list();
	}
}
