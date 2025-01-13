package testtask.shift.shopapi.model.laptop;

import lombok.Getter;
import lombok.Setter;
import testtask.shift.shopapi.model.Product;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Laptop extends Product {
    @Getter
    @Setter
    private LaptopSize size;

    public Laptop(Long id, String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock, LaptopSize size) {
        super(id, seriesNumber, producer, price, numberOfProductsInStock);
        this.size = size;
    }

    public Laptop(String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock, LaptopSize size) {
        super(seriesNumber, producer, price, numberOfProductsInStock);
        this.size = size;
    }

    public Laptop() {
    }
}
