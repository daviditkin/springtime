# Spring Boot Incremental Project

## Overview

## How To Build / Debug

## Features Implemented
 
- Application
- Endpoint
- Project Layout

```java.lang.reflect.InvocationTargetException

could not prepare statement [user lacks privilege or object not found: USER in statement [select u1_0.id,u1_0.name from User u1_0 where u1_0.id=?]] [select u1_0.id,u1_0.name from User u1_0 where u1_0.id=?]; SQL [select u1_0.id,u1_0.name from User u1_0 where u1_0.id=?]
```

# Spring Boot Application Architecture

## Layered Architecture with DTOs and Mappers

```mermaid
graph TD;
    subgraph "Client Layer"
        client[HTTP Client]
    end

    subgraph "Controller Layer"
        orderController[OrderController]
        customerController[CustomerController]
    end

    subgraph "Service Layer"
        orderService[OrderService]
        customerService[CustomerService]
    end

    subgraph "Repository Layer"
        orderRepo[OrderRepository]
        customerRepo[CustomerRepository]
    end

    subgraph "Database"
        db[(Database)]
    end

    subgraph "DTOs"
        orderDTO[OrderDTO]
        customerDTO[CustomerDTO]
        orderResponse[OrderResponse]
        customerResponse[CustomerResponse]
    end

    subgraph "Entities"
        orderEntity[Order Entity]
        customerEntity[Customer Entity]
    end

    subgraph "Mappers"
        orderMapper[OrderMapper]
        customerMapper[CustomerMapper]
    end

    %% Client to Controller connections
    client -->|HTTP Request with DTO| orderController
    client -->|HTTP Request with DTO| customerController

    %% Controller connections
    orderController -->|Validates & passes DTO| orderService
    customerController -->|Validates & passes DTO| customerService
    
    %% Controller to Response connections
    orderController -->|Returns| orderResponse
    customerController -->|Returns| customerResponse

    %% Service to Repository connections
    orderService -->|CRUD operations| orderRepo
    customerService -->|CRUD operations| customerRepo

    %% Repository to Database connections
    orderRepo -->|Persists Entities| db
    customerRepo -->|Persists Entities| db

    %% DTO and Entity Mappings
    orderDTO -->|Mapped via| orderMapper
    orderMapper -->|Maps to/from| orderEntity
    orderMapper -->|Maps to| orderResponse
    
    customerDTO -->|Mapped via| customerMapper
    customerMapper -->|Maps to/from| customerEntity
    customerMapper -->|Maps to| customerResponse

    %% Service Layer Mappings
    orderService -->|Uses| orderMapper
    customerService -->|Uses| customerMapper
```

## Component Details

### Controllers
- Handle HTTP requests/responses
- Validate incoming DTOs
- Convert HTTP status codes
- No business logic
- Use services for processing

### Services
- Contain business logic
- Transaction management
- Use mappers for DTO <-> Entity conversion
- Orchestrate multiple operations
- Call repositories for persistence

### Repositories
- Handle database operations
- Work with Entity objects
- Extend JpaRepository
- Provide CRUD operations
- Custom query methods

### DTOs (Data Transfer Objects)
- Input validation
- API contract
- Request/Response separation
- No business logic
- Immutable (Records in Java 17+)

### Entities
- JPA annotations
- Database mapping
- Domain logic
- Lifecycle callbacks
- Relationship mappings

### Mappers (MapStruct)
- Compile-time mapping generation
- Type-safe conversions
- Custom mapping methods
- Nested object mapping
- Collection mapping

## Code Structure Example

```java
// Controller Layer
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderDTO dto)
}

// Service Layer
@Service
public class OrderService {
    public OrderResponse createOrder(OrderDTO dto)
}

// Repository Layer
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

// DTO
public record OrderDTO(
    String customerName,
    List<OrderItemDTO> items
)

// Response DTO
public record OrderResponse(
    Long id,
    String customerName,
    OrderStatus status
)

// Entity
@Entity
public class Order {
    @Id @GeneratedValue
    private Long id;
    private String customerName;
}

// Mapper
@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDTO dto);
    OrderResponse toResponse(Order order);
}
```

## Data Flow

1. Client sends HTTP request with DTO
2. Controller validates DTO
3. Controller calls Service
4. Service uses Mapper to convert DTO to Entity
5. Service applies business logic
6. Service uses Repository for persistence
7. Service uses Mapper to convert Entity to Response
8. Controller returns Response to Client

## Best Practices

1. Keep layers separate and focused
2. Use DTOs for input/output
3. Entities for persistence
4. MapStruct for object mapping
5. Validate at Controller level
6. Business logic in Service layer
7. Repository for data access only
8. Clear separation of concerns