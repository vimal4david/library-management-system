package com.vdavid.apps.librarymanagementsystem.service;

import com.vdavid.apps.librarymanagementsystem.model.Inventory;
import com.vdavid.apps.librarymanagementsystem.model.ItemType;
import com.vdavid.apps.librarymanagementsystem.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    InventoryServiceImpl inventoryService;

    @Test
    void verifyCurrentInventoryDoesntReturnNull() {
        //Given

        //When
        List<Inventory> result = inventoryService.getCurrentInventory();

        //Then
        assertNotNull(result);
    }

    @Test
    void verifyCurrentInventoryIsNotEmptyWhenContainsItem() {
        //Given
        when(inventoryRepository.findAll()).thenReturn(Collections.singletonList(new Inventory(1, ItemType.BOOK.toString(), "Pi")));

        //When
        List<Inventory> result = inventoryService.getCurrentInventory();

        //Then
        assertFalse(result.isEmpty());
    }

    @Test
    void verifyOverdueItemsDoesntReturnNull() {
        //Given

        //When
        List<Inventory> result = inventoryService.getOverdueItems();

        //Then
        assertNotNull(result);
    }

    @Test
    void verifyOverdueItemsIsNotEmptyWhenItemsAreOverdue() {
        //Given
        when(inventoryRepository.findAllByBorrowedTimestampBefore(any(Instant.class)))
                .thenReturn(Collections.singletonList(new Inventory(1, ItemType.BOOK.toString(), "Pi")));

        //When
        List<Inventory> result = inventoryService.getOverdueItems();

        //Then
        assertFalse(result.isEmpty());
    }
}
