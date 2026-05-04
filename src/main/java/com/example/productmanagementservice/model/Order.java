package com.example.productmanagementservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal; // Sum of all items before tax/shipping

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal taxAmount; // Calculated tax

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal shippingCost; // Optional: good to have if you charge for delivery

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount; // Final amount: subtotal + tax + shipping

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    // Address Snapshots
    @Column(nullable = false)
    private String snapshotName;

    @Column(nullable = false)
    private String snapshotPhone;

    @Column(nullable = false)
    private String snapshotAddress;

    @Column(nullable = false)
    private String snapshotCity;

    @Column(nullable = false)
    private String snapshotState;

    @Column(nullable = false)
    private String snapshotPincode;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Relationship to OrderItems
    // CascadeType.ALL means if you save an Order, it automatically saves its items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<OrderItem> items = new ArrayList<>();


    // Helper method to add items and maintain bi-directional relationship
    public void addOrderItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}