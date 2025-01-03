# Configuration

# Issue

There are a few different types of configurations, 
but this deals with basic spring 
configuration and component configuration.

# Alternatives

1) External configuration
2) Java-based configuration with annotations

# Decision

@Configuration


Hybrid
`application.properties` for basic props
Use java-based configuration in separate top-level classes.
Profile based for different environments
Externalize all secrete / environment specific properties
