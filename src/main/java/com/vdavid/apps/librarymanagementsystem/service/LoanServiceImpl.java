package com.vdavid.apps.librarymanagementsystem.service;

import com.vdavid.apps.librarymanagementsystem.model.Inventory;
import com.vdavid.apps.librarymanagementsystem.model.Item;
import com.vdavid.apps.librarymanagementsystem.model.ItemType;
import com.vdavid.apps.librarymanagementsystem.model.User;
import com.vdavid.apps.librarymanagementsystem.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {

    private final InventoryRepository inventoryRepository;

    public LoanServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void borrowItem(User user, ItemType itemType, String title) {
        log.info("Borrowing item {} of type {} for user {}", title, itemType.name(), user.name());
        Optional<Inventory> inventoryAsOptional = inventoryRepository.findAllByIsOnLoanAndTitleAndItemType(null, title, itemType.toString()).stream().findFirst();
        if (inventoryAsOptional.isPresent()) {
            Inventory inventory = inventoryAsOptional.get();
            addItemDetailsToInventory(inventory, true, user.name(), Instant.now());
            inventoryRepository.save(inventory);
            log.info("Borrowed item {} of type {} for user {}", title, itemType.name(), user.name());
        } else {
            log.info("No items available in inventory to borrow on title: {} and type: {}", title, itemType.name());
        }
    }

    @Override
    public void returnItem(User user, ItemType itemType, String title) {
        log.info("Returning item {} of type {} for user {}", title, itemType.name(), user.name());
        Optional<Inventory> itemsInLoanAsOptional = inventoryRepository.findAllByIsOnLoanAndBorrowerAndTitleAndItemType(true, user.name(), title, itemType.toString()).stream().findAny();
        if (itemsInLoanAsOptional.isPresent()) {
            Inventory inventory = itemsInLoanAsOptional.get();
            addItemDetailsToInventory(inventory, null, null, null);
            inventoryRepository.save(inventory);
            log.info("Returned item {} of type {} for user {}", title, itemType.name(), user.name());
        } else {
            log.info("No items in loan for user {} with title {} and item type {}", user.name(), title, itemType.name());
        }
    }

    @Override
    public boolean isItemAvailableToLoan(ItemType itemType, String title) {
        return inventoryRepository.findAllByIsOnLoanAndTitleAndItemType(null, title, itemType.toString()).stream().findAny().isPresent();
    }

    @Override
    public Set<Item> getBorrowedItemsForAUser(User user) {
        return inventoryRepository.findAllByIsOnLoanAndBorrower(true, user.name()).stream().map(this::inventoryToItem).collect(Collectors.toSet());
    }

    private Item inventoryToItem(Inventory inventory) {
        return new Item(inventory.getId(), inventory.getItemId(), ItemType.valueOf(inventory.getItemType()), inventory.getTitle());
    }

    private void addItemDetailsToInventory(Inventory inventory, Boolean isOnLoan, String username, Instant borrowedTimeStamp) {
        inventory.setIsOnLoan(isOnLoan);
        inventory.setBorrower(username);
        inventory.setBorrowedTimestamp(borrowedTimeStamp);
    }
}
