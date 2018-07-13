package DBEntities;

public class UserasEntity {

    private int id;
    private String role;

    private UserEntity info;
    private AccountEntity ac;

    public UserasEntity(AccountEntity ac, UserEntity info, String role) {
        this.role = role;
        this.info = info;
        this.ac = ac;
    }

    public UserasEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserEntity getInfo() {
        return info;
    }

    public void setInfo(UserEntity info) {
        this.info = info;
    }

    public AccountEntity getAc() {
        return ac;
    }

    public void setAc(AccountEntity ac) {
        this.ac = ac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserasEntity that = (UserasEntity) o;
        if (id != that.id) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("User [id = %s, role = %s, %s, %s]", id, role, info, ac);
    }
}
