package com.vdavid.apps.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor(force = true)
@ToString
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniqueID", nullable = false)
    private Long id;

    @Column(name = "ItemID", nullable = false)
    private Integer itemId;

    @Column(name = "Type", nullable = false)
    private String itemType;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "IsOnLoan")
    private Boolean isOnLoan;

    @Column(name = "Borrower")
    private String borrower;

    @Column(name = "BorrowedTimestamp")
    private Instant borrowedTimestamp;

    public Inventory(Integer itemId, String itemType, String title) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.title = title;
        this.isOnLoan = false;
        this.borrower = null;
        this.borrowedTimestamp = null;
    }
}
