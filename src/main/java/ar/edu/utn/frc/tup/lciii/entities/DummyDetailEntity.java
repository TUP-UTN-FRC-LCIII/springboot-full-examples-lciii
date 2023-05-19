package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dummy_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    /*
    Esta anotación se utiliza para establecer una relación uno a uno entre dos entidades.
    Indica que una entidad está relacionada con otra entidad de forma exclusiva.
     */
    @JoinColumn(name = "dummy_id")
    private DummyEntity dummy;

    @Column
    private String dummyDetail;


}
