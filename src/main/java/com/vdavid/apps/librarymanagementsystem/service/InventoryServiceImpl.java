package com.vdavid.apps.librarymanagementsystem.service;

import com.vdavid.apps.librarymanagementsystem.model.Inventory;
import com.vdavid.apps.librarymanagementsystem.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final int LOAN_PERIOD_IN_DAYS = 7;
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getCurrentInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> getOverdueItems() {
        return inventoryRepository.findAllByBorrowedTimestampBefore(Instant.now().minus(LOAN_PERIOD_IN_DAYS, ChronoUnit.DAYS));
    }
}
