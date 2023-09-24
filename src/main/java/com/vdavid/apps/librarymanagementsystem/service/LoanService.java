package com.vdavid.apps.librarymanagementsystem.service;

import com.vdavid.apps.librarymanagementsystem.model.Item;
import com.vdavid.apps.librarymanagementsystem.model.ItemType;
import com.vdavid.apps.librarymanagementsystem.model.User;

import java.util.Set;

/**
 * ==> Borrow and return items - items are loaned out for a period of one week.
 * For example, a customer can borrow WarGames on 21st February and they will be expected to
 * return it by 28th February.
 * ==> Determine if a book is available.
 * ==> Determine the borrowed items for a user.
 */
public interface LoanService {
    void borrowItem(User user, ItemType itemType, String title);

    void returnItem(User user, ItemType itemType, String title);

    boolean isItemAvailableToLoan(ItemType itemType, String title);

    Set<Item> getBorrowedItemsForAUser(User user);
}
