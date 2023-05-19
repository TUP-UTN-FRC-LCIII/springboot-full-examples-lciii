package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;


@Entity
@Table(name = "dummy_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    Esta anotación se utiliza para establecer una relación muchos a uno entre dos
    entidades. Indica que varias entidades están relacionadas con una sola entidad.
     */
    @ManyToOne
    /*
    Esta anotación se utiliza para especificar la columna que se utilizará como clave
    extranjera en una relación entre entidades. Puede utilizarse para personalizar
    el nombre de la columna o establecer otras características.
     */
    @JoinColumn(name = "dummy_id", nullable=false)
    private DummyEntity dummy;

    @Column
    private String dummyItemDetail;

    @Column
    private BigDecimal dummyItemAmount;

    @Column
    private BigDecimal dummyItemQuantity;

    /*
    Esta anotación se utiliza para especificar una fórmula SQL personalizada
    que se calculará y se asignará a una propiedad de una entidad. Puede ser
    útil cuando necesitas un valor calculado en base a otras propiedades de la entidad.
     */
    @Formula("dummy_item_amount * dummy_item_quantity")
    private BigDecimal dummyItemTotal;

}
