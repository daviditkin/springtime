# Spring Project Layout

# Issue

# Alternatives

1) Group similar types of components
   - Services
   - Models
   - Controllers

2) Feature Modules (Group based on business/domain functionality)
   - User
     - UserEntity
     - UserService
     - UserController

# Decision

I'm going to use Modules because there isn't enough going on in the project to warrant anything else.

I will keep application and configuration at top-level
