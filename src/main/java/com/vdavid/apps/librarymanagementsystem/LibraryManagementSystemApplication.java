package com.vdavid.apps.librarymanagementsystem;

import com.vdavid.apps.librarymanagementsystem.service.InventoryService;
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
    }
}
