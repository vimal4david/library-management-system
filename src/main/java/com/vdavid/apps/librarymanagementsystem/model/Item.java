package com.vdavid.apps.librarymanagementsystem.model;

public record Item(Long uniqueId, Integer ItemId, ItemType itemType, String title) {
}
