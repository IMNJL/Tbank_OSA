package testtask.shift.shopapi.model.monitor;

import lombok.Getter;
import lombok.Setter;
import testtask.shift.shopapi.model.Product;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Monitor extends Product {
    @Getter
    @Setter
    private double diagonal;

    public Monitor(Long id, String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock, double diagonal) {
        super(id, seriesNumber, producer, price, numberOfProductsInStock);
        this.diagonal = diagonal;
    }

    public Monitor(String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock, double diagonal) {
        super(seriesNumber, producer, price, numberOfProductsInStock);
        this.diagonal = diagonal;
    }

    public Monitor() {

    }
}
