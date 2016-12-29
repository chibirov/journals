package com.intesys.trial.journals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intesys.trial.journals.model.Publisher;
import com.intesys.trial.journals.model.User;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByUser(User user);

}
