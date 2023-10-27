package ru.practicum.ewm.service.category;

import ru.practicum.ewm.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
