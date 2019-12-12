package mk.finki.ukim.mk.lab.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long orderId;
    private String pizzaType;
    private String clientName;
    private String clientAddress;

    public Order() {
    }

    public Order(String pizzaType, String clientName, String clientAddress, Long orderId) {
        this.pizzaType = pizzaType;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.orderId = orderId;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
