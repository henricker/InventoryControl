package model;

import java.util.Date;
import java.util.Objects;

public class Movement {
    private Integer id;
    private Integer productId;
    private Integer quantityMovement;
    private TypesMovement type;
    private Double price;
    private Date dateMovement;

    public static enum TypesMovement {
        IN,
        OUT
    }

    public Movement(Integer id, Integer productId, Integer quantityMovement, TypesMovement type, Double price, Date dateMovement) {
        this.id = id;
        this.productId = productId;
        this.quantityMovement = quantityMovement;
        this.type = type;
        this.price = price;
        this.dateMovement = dateMovement;
    }

    public Movement(Integer productId, Integer quantityMovement, TypesMovement type) {
        this.productId = productId;
        this.quantityMovement = quantityMovement;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantityMovement() {
        return quantityMovement;
    }

    public void setQuantityMovement(Integer quantityMovement) {
        this.quantityMovement = quantityMovement;
    }

    public String getType() {
        return type.name();
    }

    public void setType(TypesMovement type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateMovement() {
        return dateMovement;
    }

    public void setDateMovement(Date dateMovement) {
        this.dateMovement = dateMovement;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantityMovement=" + quantityMovement +
                ", type=" + type +
                ", price=" + price +
                ", dateMovement=" + dateMovement +
                '}';
    }
}
