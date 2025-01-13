package testtask.shift.shopapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("PMD")
@MappedSuperclass
public abstract class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String seriesNumber;

    @Getter
    @Setter
    private String producer;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private Long numberOfProductsInStock;

    public Product() {
    }

    public Product(Long id, String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock) {
        this.id = id;
        this.seriesNumber = seriesNumber;
        this.producer = producer;
        this.price = price;
        this.numberOfProductsInStock = numberOfProductsInStock;
    }

    public Product(String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock) {
        this.seriesNumber = seriesNumber;
        this.producer = producer;
        this.price = price;
        this.numberOfProductsInStock = numberOfProductsInStock;
    }
}
