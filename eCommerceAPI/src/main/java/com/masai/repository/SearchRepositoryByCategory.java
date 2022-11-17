package com.masai.repository;

import com.masai.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepositoryByCategory extends JpaRepository<Category, Integer> {
}
