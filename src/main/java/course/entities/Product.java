package course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import course.views.Views;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonView({Views.Order.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @JsonView({Views.Order.class})
    private String name;
    @JsonView({Views.Order.class})
    private String description;
    @JsonView({Views.Order.class})
    private Double price;
    @JsonView({Views.Order.class})
    private String imgUrl;

    @ManyToMany
    @JoinTable(name ="tb_product_category", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Setter(AccessLevel.NONE)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    @JsonIgnore
    public Set<Order> getOrders(){
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) {
            set.add(x.getOrder());
        }
        return set;
    }
}
