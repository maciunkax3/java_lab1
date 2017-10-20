package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.TowerService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Mag;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Tower;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class ListTowers implements Serializable {
    @ManagedProperty("#{towerService}")
    private TowerService towerService;

    public void setTowerService(TowerService wierzaService) {
        this.towerService = wierzaService;
    }
    private List<Tower> wierze;

    public List<Tower> getTower() {
        if (wierze == null) {
            wierze = towerService.findAllTowers();
        }
        return wierze;
    }

    public String removeMag(Mag m){
        for (Tower t : towerService.findAllTowers()) {
            if (t.doesMagBelong(m)) {
                t.removeMag(m);
            }
        }

        return "edit_mag?faces-redirect=true";
    }

    public void removeTower(Tower tower) {
        towerService.removeTower(tower);
        wierze.remove(tower);
    }

}
