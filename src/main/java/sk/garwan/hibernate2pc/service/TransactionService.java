package sk.garwan.hibernate2pc.service;

public interface TransactionService {

	void doInTransaction();

	void doInTransactionWithException();
	
	void doWithoutTransactionWithException();
}
