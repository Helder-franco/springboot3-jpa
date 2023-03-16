package course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import course.views.Views;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonView({Views.Order.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JsonView({Views.Order.class})
    private Instant moment;


    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;
}
