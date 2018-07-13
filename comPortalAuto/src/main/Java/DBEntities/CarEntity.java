package DBEntities;

import java.util.Date;

public class CarEntity {

    private int id;
    private String num;
    private UserasEntity owner;
    private String brend;
    private String model;
    private int power;
    private String body;
    private java.util.Date date;
    private int mileage;

    public CarEntity(String num, UserasEntity owner, String brend,
                     String model, int power, String body, Date date, int mileage) {
        this.num = num;
        this.owner = owner;
        this.date = date;
        this.brend = brend;
        this.power = power;
        this.model = model;
        this.body = body;
        this.mileage = mileage;
    }

    public CarEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public UserasEntity getOwner() {
        return owner;
    }

    public void setOwner(UserasEntity owner) {
        this.owner = owner;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        if (owner != carEntity.owner) return false;
        if (power != carEntity.power) return false;
        if (num != null ? !num.equals(carEntity.num) : carEntity.num != null) return false;
        if (brend != null ? !brend.equals(carEntity.brend) : carEntity.brend != null) return false;
        if (model != null ? !model.equals(carEntity.model) : carEntity.model != null) return false;
        if (body != null ? !body.equals(carEntity.body) : carEntity.body != null) return false;
        if (date != null ? !date.equals(carEntity.date) : carEntity.date != null) return false;
        if (mileage != carEntity.power) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = num != null ? num.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (brend != null ? brend.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + power;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + mileage;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Car [num = %s, owner = %s, brend = %s, model = %s, " +
                "power = %s, body = %s, date = %s, mileage = %s]", num, owner, brend, model, power, body, date, mileage);
    }
}
