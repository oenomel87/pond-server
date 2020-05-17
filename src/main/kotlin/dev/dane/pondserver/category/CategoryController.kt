package dev.dane.pondserver.category

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/category")
@RestController
class CategoryController (private val categoryService: CategoryService) {

}