package pl.gda.pg.eti.kask.javaee.jsf.entities;

import lombok.*;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mag implements Serializable {
    String name;
    int mana;
    Enviroment env;
    int id;

}
