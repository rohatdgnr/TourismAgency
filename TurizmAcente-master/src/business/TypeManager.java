package business;

import core.Helper;
import dao.TypeDao;
import entity.Types;

import java.util.ArrayList;
import java.util.List;

public class TypeManager {
    private TypeDao typeDao ;
    public TypeManager() {

        this.typeDao = new TypeDao();

    }


    public ArrayList<Types> findAll(int id){
        return this.typeDao.findAll(id);
    }


    public ArrayList<Object[]>getForTable(int size,int id){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for(Types obj : this.findAll(id)){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getTypeName();
            brandRowList.add(rowObject);

        }
        return brandRowList;
    }

    public Types getById(int id) {
        return this.typeDao.getById(id);
    }
    public String getByTypeId(int id) {
        return this.typeDao.getByTypeId(id);
    }



    public boolean update (int hotelId, List<String> rightList){
        if(hotelId ==0 ){
            Helper.showMsg(" ID bulunamadı");
            return false;
        }
        return this.typeDao.update(hotelId,rightList);
    }
    public boolean save(int hotelId, List<String> rightList){
        return this.typeDao.save(hotelId,rightList);
    }
    public boolean deleteType(int hotelId,List<String> typeNames ){
        if(hotelId ==0 ){
            Helper.showMsg(" ID bulunamadı");
            return false;
        }
        return this.typeDao.deleteType(hotelId,typeNames);
    }

}
