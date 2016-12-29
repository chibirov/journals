package com.intesys.trial.journals.repository;

import org.springframework.data.repository.CrudRepository;

import com.intesys.trial.journals.model.Journal;
import com.intesys.trial.journals.model.Publisher;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface JournalRepository extends CrudRepository<Journal, Long> {

    Collection<Journal> findByPublisher(Publisher publisher);

    List<Journal> findByCategoryIdIn(List<Long> ids);
    
    List<Journal> findByPublishDateBetween(Date startDate, Date endDate);

}
