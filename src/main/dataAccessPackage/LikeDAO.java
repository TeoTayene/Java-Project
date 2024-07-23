package main.dataAccessPackage;

import main.modelPackage.LikeModel;
import main.exceptionPackage.LikeSearchException;

import java.sql.Date;
import java.util.List;

public interface LikeDAO {
    public List<LikeModel> getLikesBetween(Date startDate, Date endDate) throws LikeSearchException;
}
