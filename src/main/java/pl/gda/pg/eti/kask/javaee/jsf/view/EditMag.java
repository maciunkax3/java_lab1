package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.TowerService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Mag;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Tower;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean
@Log
public class EditMag {
    @ManagedProperty("#{towerService}")
    private TowerService towerService;

    @Getter
    @Setter
    private int towerId;
    @Getter
    @Setter
    private int magId;
    @Getter
    @Setter
    private Tower tower;
    @Getter
    @Setter
    private Mag mag;
    @Getter
    @Setter
    List<Tower> towers;

    private List<SelectItem> towersAsSelectItems;

    public void setTowerService(TowerService towerService) {
        this.towerService = towerService;
    }

    public List<SelectItem> getTowersAsSelectItems() {
        if (towersAsSelectItems == null) {
            towersAsSelectItems = new ArrayList<>();
            for (Tower t : towerService.findAllTowers()) {
                towersAsSelectItems.add(new SelectItem(t, "Wieza "+Integer.toString(t.getId())));
            }
        }
        return towersAsSelectItems;
    }

    public void init() {

        towers = towerService.findAllTowers();
        if (tower == null && towerId != 0) {
            tower = towerService.findTower(towerId);
            if(mag==null && magId != 0){
                for(Mag m : tower.getMags()){
                    if (m.getId()==magId){
                        mag=m;
                        break;
                    }
                };
            }
        } else if (mag==null && magId == 0) {
            mag = new Mag();
        }
    }

    public void saveMag() {
        if(tower!=null) {
            if (mag.getId() == 0) {
                mag.setId(tower.getMags().size() + 1);
                tower.addMag(mag);
            } else if (tower.doesMagBelong(mag)) ;
            else {
                for (Tower t : towerService.findAllTowers()) {
                    if (t.doesMagBelong(mag)) {
                        Mag tmp = mag;
                        t.removeMag(mag);
                        tower.addMag(mag);
                        break;
                    }
                }
            }
        }
    }
}
