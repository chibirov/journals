package com.intesys.trial.journals.service;

import java.util.List;

import com.intesys.trial.journals.model.Journal;
import com.intesys.trial.journals.model.Publisher;
import com.intesys.trial.journals.model.User;

public interface JournalService {

	List<Journal> listAll(User user);

	List<Journal> publisherList(Publisher publisher);
	
	List<Journal> getNew();

	Journal publish(Publisher publisher, Journal journal, Long categoryId);

	void unPublish(Publisher publisher, Long journalId);
}
