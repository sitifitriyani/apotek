package com.tugas5.apotek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tugas5.apotek.models.Supplier;
import com.tugas5.apotek.services.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
        @Autowired
    private SupplierService supplierService;
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("obat", new Supplier());
        return "add-supplier";
    }


    @PostMapping("/add")
    public String addSupplier(@ModelAttribute Supplier supplier, Model model) {
        supplierService.addSupplier(supplier);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
        return "redirect:/";
    }

}
