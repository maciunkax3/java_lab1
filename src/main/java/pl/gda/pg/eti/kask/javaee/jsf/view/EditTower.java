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
public class EditTower {
    @ManagedProperty("#{towerService}")
    private TowerService towerService;

    @Getter
    @Setter
    private int towerId;

    @Getter
    @Setter
    private Tower tower;

    public void setTowerService(TowerService towerService) {
        this.towerService = towerService;
    }

    public void init() {
        if (towerId != 0) {
            tower = towerService.findTower(towerId);
        } else tower = new Tower();
    }

    public void saveTower() {
        if (tower.getId() == 0) {
            tower.setId(towerService.towers.lastKey() + 1);
            towerService.towers.put(tower.getId(), tower);
        }
    }
}