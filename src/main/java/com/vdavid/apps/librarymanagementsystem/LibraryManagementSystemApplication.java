package com.vdavid.apps.librarymanagementsystem;

import com.vdavid.apps.librarymanagementsystem.model.ItemType;
import com.vdavid.apps.librarymanagementsystem.model.User;
import com.vdavid.apps.librarymanagementsystem.service.InventoryService;
import com.vdavid.apps.librarymanagementsystem.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(LibraryManagementSystemApplication.class, args);
        InventoryService inventoryService = applicationContext.getBean(InventoryService.class);
        log.info("Current Inventory: {}", inventoryService.getCurrentInventory());

        LoanService loanService = applicationContext.getBean(LoanService.class);
        loanService.borrowItem(new User("vdavid"), ItemType.BOOK, "Pi");
        loanService.borrowItem(new User("vdavid"), ItemType.BOOK, "Pidkjfdkjfdkj");
        log.info("Updated Inventory: {}", inventoryService.getCurrentInventory());
        log.info("Borrow items for user vdavid: {}", loanService.getBorrowedItemsForAUser(new User("vdavid")));

        loanService.returnItem(new User("vdavid"), ItemType.BOOK, "Pi");
        log.info("Updated Inventory: {}", inventoryService.getCurrentInventory());
    }
}
