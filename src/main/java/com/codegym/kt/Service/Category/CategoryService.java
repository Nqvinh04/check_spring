package com.codegym.kt.Service.Category;

import com.codegym.kt.model.Category;
import com.codegym.kt.model.User;

public interface CategoryService {
    Iterable<Category> findAll();
    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}
