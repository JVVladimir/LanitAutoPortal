package DBEntities;

public class AccountEntity {

    private int id;
    private String login;
    private String pass;
    private String stat;

    public AccountEntity(String login, String pass, String stat) {
        this.login = login;
        this.pass = pass;
        this.stat = stat;
    }

    public AccountEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (stat != null ? !stat.equals(that.stat) : that.stat != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Account [id = %s, login = %s, pass = %s, stat = %s]", id, login, pass, stat);
    }

}
