package com.tugas5.apotek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tugas5.apotek.models.Category;
import com.tugas5.apotek.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
        @Autowired
    private CategoryService categoryService;
    
    @GetMapping("")
    public String showCategoryList(Model model, 
            @ModelAttribute("message") String message) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("message", message);
        return "category";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("categories", new Category());
        return "add-category";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "update-category";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Integer id, @ModelAttribute Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/category";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = categoryService.deleteCategory(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("message", 
                "Cannot delete this category because it is being used in products!");
        } else {
            redirectAttributes.addFlashAttribute("message", 
                "Category successfully deleted!");
        }
        return "redirect:/category";
    }
}
