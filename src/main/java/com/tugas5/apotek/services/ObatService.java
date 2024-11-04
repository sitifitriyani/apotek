package com.tugas5.apotek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.repositories.ObatRepository;

import java.util.List;

@Service
public class ObatService {
    @Autowired
    private ObatRepository obatRepository;

    public List<Obat> getAllObat() {
        return obatRepository.findAll();
    }

    public Obat addObat(Obat obat) {
        return obatRepository.save(obat);
    }

    public void deleteObat(Integer id) {
        obatRepository.deleteById(id);
    }

    public Obat getObatById(Integer id) {
        return obatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obat not found"));
    }

    public Obat updateObat(Integer id, Obat obat) {
        Obat existingObat = getObatById(id);
        existingObat.setNamaObat(obat.getNamaObat());
        existingObat.setPrice(obat.getPrice());
        existingObat.setDescription(obat.getDescription());
        existingObat.setCategory(obat.getCategory());
        existingObat.setSupplier(obat.getSupplier());
        return obatRepository.save(existingObat);
    }
}
