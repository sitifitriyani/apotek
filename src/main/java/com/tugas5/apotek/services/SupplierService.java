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

     public boolean canDeleteSupplier(Integer id) {
        Supplier supplier = getSupplierById(id);
        return supplier.getObats().isEmpty();
    }

    public boolean deleteSupplier(Integer id) {
        if (!canDeleteSupplier(id)) {
            return false;
        }
        supplierRepository.deleteById(id);
        return true;
    }

    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public Supplier updateSupplier(Integer id, Supplier supplier) {
        Supplier existingSupplier = getSupplierById(id);
        existingSupplier.setName(supplier.getName());
        existingSupplier.setAddress(supplier.getAddress());
        existingSupplier.setPhone(supplier.getPhone());
        return supplierRepository.save(existingSupplier);
    }
}
