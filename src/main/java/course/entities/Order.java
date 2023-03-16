package course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import course.entities.enums.OrderStatus;
import course.views.Views;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@JsonView({Views.Order.class})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;


    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;


    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();


    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL )
    private Payment payment;
    public Order(Long id, Instant moment, @NotNull OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
       setOrderStatus(orderStatus);
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(@NotNull OrderStatus orderStatus) {
        this.orderStatus = orderStatus.getCode();
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getTotal(){
        double sum = 0.0;
        for(OrderItem x : items){
            sum += x.getSubTotal();
        }
        return sum;
    }
}
