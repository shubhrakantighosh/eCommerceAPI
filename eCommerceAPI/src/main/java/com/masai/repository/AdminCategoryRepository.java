package com.masai.repository;

import com.masai.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCategoryRepository extends JpaRepository<Category,Integer> {
}
