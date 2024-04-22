package business;

import core.Helper;
import dao.SeasonDao;
import entity.Property;
import entity.Season;


import java.util.ArrayList;
import java.util.List;

public class SeasonManager {
    private SeasonDao seasonDao ;
    public SeasonManager() {
        // this.con = Db.getInstance();
        this.seasonDao = new SeasonDao();

    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }
    public Season getSeasonDate(int seasonId) {
        return this.seasonDao.getSeasonDate(seasonId);
    }


    public List<Season> getBySeasonList(int id) {
        return this.seasonDao.getBySeasonList(id);
    }
    public ArrayList<Season> findAll(int id){
        return this.seasonDao.findAll(id);
    }

    public ArrayList<Object[]>getForTable(int size,int id){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for(Season obj : this.findAll(id)){
            Object[] rowObject = new Object[size];
            int i = 0;

            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getSeason_start();
            rowObject[i++] = obj.getSeason_end();
            rowObject[i++] = obj.getSeasonName();

            brandRowList.add(rowObject);

        }
        return brandRowList;
    }
    public boolean save(List<Season> seasons, int hotelId){
        return this.seasonDao.save(seasons,hotelId);
    }

  public boolean update (Season season){
        if(season.getHotel_id()==0 ){
            Helper.showMsg(" ID bulunamadı");
            return false;
        }
        return this.seasonDao.update(season);
    }


    public Season getBySeason(int id) {
        return this.seasonDao.getBySeason(id);
    }

}
