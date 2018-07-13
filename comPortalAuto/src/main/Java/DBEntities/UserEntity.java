package DBEntities;

public class UserEntity {

    private int id;
    private String fio;
    private String addr;
    private String mphone;
    private String email;

    public UserEntity(String fio, String addr, String mphone, String email) {
        this.fio = fio;
        this.addr = addr;
        this.mphone = mphone;
        this.email = email;
    }

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        if (id != that.id) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;
        if (mphone != null ? !mphone.equals(that.mphone) : that.mphone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (mphone != null ? mphone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("User [id = %s, fio = %s, addr = %s, mphone = %s, " +
                "email = %s]", id, fio, addr, mphone, email);
    }
}