package com.vdavid.apps.librarymanagementsystem.service;

import com.vdavid.apps.librarymanagementsystem.model.Inventory;
import com.vdavid.apps.librarymanagementsystem.model.Item;
import com.vdavid.apps.librarymanagementsystem.model.ItemType;
import com.vdavid.apps.librarymanagementsystem.model.User;
import com.vdavid.apps.librarymanagementsystem.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {
    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private LoanServiceImpl loanService;


    @Test
    void verifySaveMethodIsCalledOnWhenItemIsBorrowedSuccessfully() {
        //Given
        when(inventoryRepository.findAllByIsOnLoanAndTitleAndItemType(null, "dummyTitle", String.valueOf(ItemType.BOOK)))
                .thenReturn(Collections.singletonList(new Inventory(1, ItemType.BOOK.toString(), "dummyTitle")));

        //When
        loanService.borrowItem(new User("testuser"), ItemType.BOOK, "dummyTitle");

        //Then
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

    @Test
    void verifySaveMethodIsNotCalledOnWhenItemIsNotBorrowed() {
        //Given

        //When
        loanService.borrowItem(new User("testuser"), ItemType.BOOK, "dummyTitle");

        //Then
        verify(inventoryRepository, never()).save(any(Inventory.class));
    }

    @Test
    void verifySaveMethodIsCalledOnWhenItemReturnedSuccessfully() {
        //Given
        when(inventoryRepository.findAllByIsOnLoanAndBorrowerAndTitleAndItemType(true, "testuser", "dummyTitle", String.valueOf(ItemType.BOOK)))
                .thenReturn(Collections.singletonList(new Inventory(1, ItemType.BOOK.toString(), "dummyTitle")));

        //When
        loanService.returnItem(new User("testuser"), ItemType.BOOK, "dummyTitle");

        //Then
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

    @Test
    void verifyReturnMethodIsNotCalledOnWhenItemIsNotBorrowed() {
        //Given

        //When
        loanService.returnItem(new User("testuser"), ItemType.BOOK, "dummyTitle");

        //Then
        verify(inventoryRepository, never()).save(any(Inventory.class));
    }


    @Test
    void returnTrueIfItemAvailableToLoan() {
        //Given
        when(inventoryRepository.findAllByIsOnLoanAndTitleAndItemType(null, "dummyTitle", String.valueOf(ItemType.BOOK)))
                .thenReturn(Collections.singletonList(new Inventory(1, ItemType.BOOK.toString(), "dummyTitle")));

        //When
        boolean result = loanService.isItemAvailableToLoan(ItemType.BOOK, "dummyTitle");

        //Then
        assertTrue(result);
    }

    @Test
    void returnFalseIfItemsNotAvailableToLoan() {
        //Given
        when(inventoryRepository.findAllByIsOnLoanAndTitleAndItemType(null, "dummyTitle", String.valueOf(ItemType.BOOK)))
                .thenReturn(Collections.emptyList());

        //When
        boolean result = loanService.isItemAvailableToLoan(ItemType.BOOK, "dummyTitle");

        //Then
        assertFalse(result);
    }

    @Test
    void verifyResultIsNotEmptyWhenUserBorrowedItems() {
        //Given
        when(inventoryRepository.findAllByIsOnLoanAndBorrower(true, "testuser"))
                .thenReturn(Collections.singletonList(new Inventory(1, ItemType.BOOK.toString(), "dummyTitle")));

        //When
        Set<Item> result = loanService.getBorrowedItemsForAUser(new User("testuser"));

        //Then
        assertFalse(result.isEmpty());
    }

    @Test
    void verifyResultIsEmptyWhenUserHaventBorrowedAnyItems() {
        //Given
        when(inventoryRepository.findAllByIsOnLoanAndBorrower(true, "testuser"))
                .thenReturn(Collections.emptyList());

        //When
        Set<Item> result = loanService.getBorrowedItemsForAUser(new User("testuser"));

        //Then
        assertTrue(result.isEmpty());
    }
}
