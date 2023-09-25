package com.vdavid.apps.librarymanagementsystem.repository;

import com.vdavid.apps.librarymanagementsystem.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findAllByIsOnLoanAndTitleAndItemType(Boolean isOnLoan, String title, String itemType);

    List<Inventory> findAllByIsOnLoanAndBorrowerAndTitleAndItemType(Boolean isOnLoan, String userName, String title, String itemType);

    List<Inventory> findAllByIsOnLoanAndBorrower(Boolean isOnLoan, String userName);
}
