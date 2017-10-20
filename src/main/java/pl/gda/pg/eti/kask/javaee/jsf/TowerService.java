package pl.gda.pg.eti.kask.javaee.jsf;


import pl.gda.pg.eti.kask.javaee.jsf.entities.Enviroment;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Mag;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Tower;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


@ManagedBean
@ApplicationScoped
public class TowerService {

    public final SortedMap<Integer, Tower> towers;

    public TowerService(){
        Tower w1 = new Tower(1, 122);
        Tower w2 = new Tower(2, 997);
        w1.addMag(new Mag("Czeslaw", 223, Enviroment.OGIEN, 1));
        w2.addMag(new Mag("Anuiak", 118, Enviroment.WODA, 2));
        w1.addMag(new Mag("Maslana", 198, Enviroment.OGIEN, 3));
        w2.addMag(new Mag("Konieczko", 315, Enviroment.WIATR, 4));
        towers = new TreeMap<>();
        towers.put(w1.getId(), w1);
        towers.put(w2.getId(), w2);
    }

    public List<Tower> findAllTowers() {
        return new ArrayList<>(towers.values());
    }

    public void removeTower(Tower wierza){towers.remove(wierza.getId()); }

    public Tower findTower(int id) {
        return towers.get(id);
    }


    public void saveTower(Tower tower) {
        if (tower.getId() == 0) {
            tower.setId(towers.lastKey() + 1);
        }
        towers.put(tower.getId(), tower);
    }
    public void saveMag(Tower tower, Mag m) {
        if (m.getId() == 0) {
            m.setId(tower.getMags().size()+1);
            tower.addMag(m);
        }
    }
}
