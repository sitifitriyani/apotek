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

import com.tugas5.apotek.models.Supplier;
import com.tugas5.apotek.services.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    
    @GetMapping("")
    public String showSupplierList(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSupplier());
        return "supplier";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "add-supplier";
    }

    @PostMapping("/add")
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierService.addSupplier(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = supplierService.deleteSupplier(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("message", 
                "Cannot delete this category because it is being used in products!");
        } else {
            redirectAttributes.addFlashAttribute("message", 
                "Category successfully deleted!");
        }
        return "redirect:/supplier";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierById(id));
        return "update-supplier";
    }

    @PostMapping("/edit/{id}")
    public String updateSupplier(@PathVariable Integer id, @ModelAttribute Supplier supplier) {
        supplierService.updateSupplier(id, supplier);
        return "redirect:/supplier";
    }
}
