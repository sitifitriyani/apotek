package com.tugas5.apotek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tugas5.apotek.services.ObatService;
import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.repositories.CategoryRepository;
import com.tugas5.apotek.repositories.SupplierRepository;

@Controller
@RequestMapping("/obat")
public class ObatController {
    @Autowired
    private ObatService obatService;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("obats", obatService.getAllObat());
        return "home";
    }
   @PostMapping("/add")
    public String addObat(@ModelAttribute Obat obat) {
        obatService.addObat(obat);
        return "redirect:/obat";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "add-obat";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("obat", obatService.getObatById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "update-obat";
    }

    @PostMapping("/edit/{id}")
    public String updateObat(@PathVariable Integer id, @ModelAttribute Obat obat) {
        obatService.updateObat(id, obat);
        return "redirect:/obat";
    }

    @GetMapping("/delete/{id}")
    public String deleteObat(@PathVariable Integer id) {
        obatService.deleteObat(id);
        return "redirect:/obat";
    }
}
