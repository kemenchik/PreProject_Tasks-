package userApp.repos;

public enum RoleId {
    USER (1),
    ADMIN (2);

    private long id;

    RoleId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
