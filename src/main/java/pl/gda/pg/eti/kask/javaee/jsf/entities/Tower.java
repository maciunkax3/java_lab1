package pl.gda.pg.eti.kask.javaee.jsf.entities;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tower implements Serializable {
    private int id;
    private int height;
    private List<Mag> mags = new ArrayList<>();

    public Tower(int id, int height){
        this.height=height;
        this.id=id;
    }
    public void addMag(Mag m){
        mags.add(m);
    }
    public void removeMag(Mag m){mags.remove(m);}
    public boolean doesMagBelong(Mag m){
        for(Mag mm : mags)
            if(mm==m)
                return true;
        return false;
    }
}
