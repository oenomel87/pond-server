package dev.dane.pondserver.category.domain.repository

import dev.dane.pondserver.category.domain.entity.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Long>