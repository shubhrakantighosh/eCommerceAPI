package com.masai.repository;

import com.masai.model.Category;
import com.masai.model.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Query("select new com.masai.model.CategoryDTO(category.categoryId, category.categoryName) from Category category")
    List<CategoryDTO> categories();

    @Query("select category.categoryId from Category category where category.categoryName=?1")
    Integer findCategoryByName(String categoryName);

}
