package com.intesys.trial.journals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intesys.trial.journals.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
