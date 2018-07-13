package DBEntities;

import java.util.Date;

public class PromoEntity {


    private int id;
    private UserasEntity owner;
    private Date date;
    private CarEntity car;
    private int price;
    private String info;
    private String stat;

    public PromoEntity(UserasEntity owner, Date date, CarEntity car, int price, String info, String stat) {
        this.owner = owner;
        this.date = date;
        this.car = car;
        this.price = price;
        this.info = info;
        this.stat = stat;
    }

    public PromoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserasEntity getOwner() {
        return owner;
    }

    public void setOwner(UserasEntity owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoEntity that = (PromoEntity) o;
        if (id != that.id) return false;
        if (owner != that.owner) return false;
        if (price != that.price) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (stat != null ? !stat.equals(that.stat) : that.stat != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Promo [id = %s, owner = %s, date = %s, car = %s, price = %s, " +
                "info = %s, stat = %s]", id, owner, date, car, price, info, stat);
    }
}
