package sk.garwan.hibernate2pc;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sk.garwan.hibernate2pc.domain.Author;
import sk.garwan.hibernate2pc.domain.Book;
import sk.garwan.hibernate2pc.service.AuthorService;
import sk.garwan.hibernate2pc.service.BookService;
import sk.garwan.hibernate2pc.service.TransactionService;


public class TransactionTest {

	@Test
	public void doTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "datasource1.xml", "datasource2.xml",
						"transactionContext.xml" });

		TransactionService ts = ctx.getBean(TransactionService.class);
		BookService bs = ctx.getBean(BookService.class);
		AuthorService as = ctx.getBean(AuthorService.class);

		ts.doInTransaction();

		List<Book> books = bs.loadAllBooks();
		Assert.assertEquals("Book wasn't created", 1, books.size());

		List<Author> authors = as.loadAllAuthors();
		Assert.assertEquals("Author wasn't created", 1, authors.size());

		try {
			ts.doInTransactionWithException();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		books = bs.loadAllBooks();
		Assert.assertEquals("Book was created", 1, books.size());

		authors = as.loadAllAuthors();
		Assert.assertEquals("Author was created", 1, authors.size());

		try {
			ts.doWithoutTransactionWithException();
			Assert.fail("Exception expected");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		books = bs.loadAllBooks();
		Assert.assertEquals("Book was created", 1, books.size());

		authors = as.loadAllAuthors();
		Assert.assertEquals("Author wasn't created", 2, authors.size());
	}
}
