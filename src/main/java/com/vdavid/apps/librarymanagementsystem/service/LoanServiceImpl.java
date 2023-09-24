package com.vdavid.apps.librarymanagementsystem.service;

import com.vdavid.apps.librarymanagementsystem.model.Item;
import com.vdavid.apps.librarymanagementsystem.model.ItemType;
import com.vdavid.apps.librarymanagementsystem.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LoanServiceImpl implements LoanService {

    @Override
    public void borrowItem(User user, ItemType itemType, String title) {

    }

    @Override
    public void returnItem(User user, ItemType itemType, String title) {

    }

    @Override
    public boolean isItemAvailableToLoan(ItemType itemType, String title) {
        return false;
    }

    @Override
    public Set<Item> getBorrowedItemsForAUser(User user) {
        return null;
    }
}
