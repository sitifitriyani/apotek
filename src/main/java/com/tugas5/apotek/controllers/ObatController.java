package com.tugas5.apotek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.services.ObatService;

@Controller
@RequestMapping("/obat")
public class ObatController {
    @Autowired
    private ObatService obatService;
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("obat", new Obat());
        return "add-obat";
    }

    @PostMapping("/add")
    public String addObat(@ModelAttribute Obat obat, Model model) {
        obatService.addObat(obat);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteObat(@PathVariable Integer id) {
        obatService.deleteObat(id);
        return "redirect:/";
    }
}
