package com.vdavid.apps.librarymanagementsystem.service;

import com.vdavid.apps.librarymanagementsystem.model.Inventory;

import java.util.List;

/**
 * ==> Determine current inventory - this should show you the current items that are loanable. You
 * should make allowances for multiple copies of the same item (i.e. there can be multiple copies of
 * the same book/movie).
 * For example, if you choose to use the initial inventory, the current inventory should return the
 * titles.
 * ==> Determine overdue items. i.e. all items that should have been returned before today.
 * For example, if a book was due on 12th February and today is 15th February, that book should be
 * flagged as overdue.
 */
public interface InventoryService {
    List<Inventory> getCurrentInventory();

    List<Inventory> getOverdueItems();
}
