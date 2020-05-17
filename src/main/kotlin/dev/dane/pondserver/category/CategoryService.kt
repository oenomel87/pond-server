package dev.dane.pondserver.category

import dev.dane.pondserver.category.domain.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService (private val categoryRepository: CategoryRepository) {

}