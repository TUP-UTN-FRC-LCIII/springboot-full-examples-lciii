package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.entities.DummyDetailEntity;
import ar.edu.utn.frc.tup.lciii.entities.DummyItemEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dummy {

    private Long id;
    @NotEmpty(message = "Dummy Info must not by Empty")
    private String dummyInfo;
    private List<DummyItem> dummyItems;
    private DummyDetail dummyDetail;
    private DummyType type;
    private LocalDateTime createdDate;
}
