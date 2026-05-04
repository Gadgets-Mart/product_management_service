package com.example.productmanagementservice.service;



import com.example.productmanagementservice.GlobalExceptionHandler.ResourceNotFoundException;
import com.example.productmanagementservice.dto.OrderDto.OrderResponseDTO;
import com.example.productmanagementservice.dto.OrderDto.OrderItemRequestDTO;
import com.example.productmanagementservice.dto.OrderDto.OrderItemResponseDTO;
import com.example.productmanagementservice.model.*;
import com.example.productmanagementservice.dto.OrderDto.OrderRequestDTO;
import com.example.productmanagementservice.repository.OrderRepository;
import com.example.productmanagementservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;
    @Autowired
    private ProductRepository productRepo;

    public OrderResponseDTO getOrderById(Long id) {
        Order order = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        return mapToResponseDTO(order);
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        // 1. Convert RequestDTO to Entity
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setStatus(OrderStatus.PENDING);

        // Map Address Snapshots
        order.setSnapshotName(dto.getName());
        order.setSnapshotPhone(dto.getPhone());
        order.setSnapshotAddress(dto.getAddress());
        order.setSnapshotCity(dto.getCity());
        order.setSnapshotState(dto.getState());
        order.setSnapshotPincode(dto.getPincode());

        // 2. Process Items and Calculate Totals
        BigDecimal subtotal = BigDecimal.ZERO;

        if (dto.getItems() != null) {
            for (OrderItemRequestDTO itemDto : dto.getItems()) {
                Product product = productRepo.findById(itemDto.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + itemDto.getProductId()));

                OrderItem item = new OrderItem();
                item.setProductId(itemDto.getProductId());
                item.setQuantity(itemDto.getQuantity());

                // 2. Set the REAL price from the database, NOT a mock value
                double realPrice = product.getPrice();
                item.setPriceAtPurchase(realPrice);

                // 3. Calculate subtotal using the real price
                BigDecimal itemTotal = BigDecimal.valueOf(realPrice).multiply(new BigDecimal(itemDto.getQuantity()));
                subtotal = subtotal.add(itemTotal);
                // Link item to order (using your helper method)
                order.addOrderItem(item);
            }
        }

        // 3. Set Costs
        order.setSubtotal(subtotal);
        order.setTaxAmount(subtotal.multiply(new BigDecimal("0.18"))); // 18% GST/Tax
        order.setShippingCost(new BigDecimal("40.00"));
        order.setTotalAmount(subtotal.add(order.getTaxAmount()).add(order.getShippingCost()));

        // 4. Save and return as ResponseDTO
        Order savedOrder = repo.save(order);
        return mapToResponseDTO(savedOrder);
    }

    public OrderResponseDTO cancelOrder(Long id) {
        Order order = repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Cannot Cancel.Order not found:" + id));

        order.setStatus(OrderStatus.CANCELLED);
        return mapToResponseDTO(repo.save(order));
    }

    public OrderResponseDTO updateStatus(Long id, OrderStatus status) {
        Order order = repo.findById(id).orElse(null);
        if (order == null) return null;

        order.setStatus(status);
        return mapToResponseDTO(repo.save(order));
    }

    // Helper: Convert Entity -> ResponseDTO (Prevents Infinite Loops)
    private OrderResponseDTO mapToResponseDTO(Order order) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        response.setUserId(order.getUserId());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponseDTO> itemDtos = order.getItems().stream().map(item -> {
            OrderItemResponseDTO iDto = new OrderItemResponseDTO();
            iDto.setProductId(item.getProductId());
            iDto.setQuantity(item.getQuantity());
            iDto.setPriceAtPurchase(item.getPriceAtPurchase());
            return iDto;
        }).collect(Collectors.toList());

        response.setItems(itemDtos);
        return response;
    }

    public List<OrderResponseDTO> getOrderHistory(String userId){
        List<Order> orders=repo.findByUserIdOrderByCreatedAtDesc(userId);

        return orders.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
}