package russianhackers.api.security;

public enum ApplicationUserPermission {
    JOURNAL_READ("journal:read"),
    JOURNAL_WRITE("journal:write"),
    RECORD_READ("record:write"),
    RECORD_WRITE("record:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
