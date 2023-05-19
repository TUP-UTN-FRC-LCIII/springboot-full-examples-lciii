package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.entities.DummyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyItem {

    private String dummyItemDetail;
    private BigDecimal dummyItemAmount;
    private BigDecimal dummyItemQuantity;
    private BigDecimal dummyItemTotal;
}
