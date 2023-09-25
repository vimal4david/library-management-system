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
        log.info("Scenario 1 ==> Current Inventory: {}", inventoryService.getCurrentInventory());
        log.info("Scenario 2 ==> Overdue Items: {}", inventoryService.getOverdueItems());

        LoanService loanService = applicationContext.getBean(LoanService.class);
        log.info("Scenario 3 ==> Borrow valid book");
        loanService.borrowItem(new User("vdavid"), ItemType.BOOK, "Pi");
        log.info("==> Updated Inventory after user vdavid borrowed a book with title Pi: {}", inventoryService.getCurrentInventory());
        log.info("==> Borrowed items for the user vdavid: {}", loanService.getBorrowedItemsForAUser(new User("vdavid")));
        log.info("==> Is book title Pi available to loan: {}", loanService.isItemAvailableToLoan(ItemType.BOOK, "Pi"));

        log.info("Scenario 4 ==> Borrow invalid book");
        loanService.borrowItem(new User("vdavid"), ItemType.BOOK, "Invalid Book");

        log.info("Scenario 5 ==> Return valid book");
        loanService.returnItem(new User("vdavid"), ItemType.BOOK, "Pi");
        loanService.returnItem(new User("vdavid"), ItemType.BOOK, "Invalid Book");
        log.info("==> Updated Inventory after user vdavid returned a book with title Pi: {}", inventoryService.getCurrentInventory());
        log.info("==> Borrowed items for the user vdavid: {}", loanService.getBorrowedItemsForAUser(new User("vdavid")));
        log.info("==> Is book title Pi available to loan: {}", loanService.isItemAvailableToLoan(ItemType.BOOK, "Pi"));

        log.info("Scenario 6 ==> Return invalid book");
        loanService.returnItem(new User("vdavid"), ItemType.BOOK, "Invalid Book");
    }
}
