package com.tugas5.apotek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas5.apotek.models.Supplier;
import com.tugas5.apotek.repositories.SupplierRepository;
import java.util.List;

@Service
public class SupplierService {
        @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
    }

}
