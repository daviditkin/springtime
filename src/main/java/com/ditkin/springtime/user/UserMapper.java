package com.ditkin.springtime.user;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")  // This makes it injectable as a Spring bean
public interface UserMapper {
    // Basic mapping methods
    User toEntity(UserDTO userDTO);
    UserResponse toResponse(User user);

    // Custom mapping method with specific logic
//    @Mapping(target = "totalAmount", source = "total")
//    @Mapping(target = "orderDate", expression = "java(new java.util.Date())")
//    @Mapping(target = "items", source = "orderItems")
//    OrderResponse toResponseWithCustomMappings(Order order);
//
//    // Handle nested objects (OrderItem)
//    OrderItem toOrderItem(OrderItemDTO itemDTO);
//
//    // You can also provide default methods for complex mappings
//    default OrderStatus mapStatus(String status) {
//        return OrderStatus.valueOf(status.toUpperCase());
//    }
}