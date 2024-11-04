package com.tugas5.apotek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tugas5.apotek.models.Category;
import com.tugas5.apotek.models.Supplier;
import com.tugas5.apotek.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
        @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("obat", new Supplier());
        return "add-category";
    }


    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, Model model) {
        categoryService.addCategory(category);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/";
    }
}
