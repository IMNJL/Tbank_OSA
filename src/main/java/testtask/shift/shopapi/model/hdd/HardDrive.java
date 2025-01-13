package testtask.shift.shopapi.model.hdd;

import lombok.Getter;
import lombok.Setter;
import testtask.shift.shopapi.model.Product;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class HardDrive extends Product {
    @Getter
    @Setter
    private double capacity;

    public HardDrive(Long id, String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock, double capacity) {
        super(id, seriesNumber, producer, price, numberOfProductsInStock);
        this.capacity = capacity;
    }

    public HardDrive(String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock, double capacity) {
        super(seriesNumber, producer, price, numberOfProductsInStock);
        this.capacity = capacity;
    }

    public HardDrive() {

    }
}
