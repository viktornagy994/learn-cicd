package hu.progmasters.kingfishair.config;

public enum UserRole {
    
    ROLE_ADMIN("ADMIN"),
    
    ROLE_USER("USER");
    
    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
