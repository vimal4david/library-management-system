package com.vdavid.apps.librarymanagementsystem.repository;

import com.vdavid.apps.librarymanagementsystem.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
