package com.vdavid.apps.librarymanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @Id
    @Column(name = "UniqueID", nullable = false)
    private Long id;

    @Column(name = "ItemID", nullable = false)
    private Integer itemId;

    @Column(name = "Type", nullable = false)
    private String itemType;

    @Column(name = "Title", nullable = false)
    private String title;
}
