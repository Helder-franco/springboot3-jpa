package course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import course.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;


import java.io.Serializable;
import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

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
}
