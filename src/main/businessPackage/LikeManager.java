package main.businessPackage;

import main.dataAccessPackage.LikeDataAccess;
import main.dataAccessPackage.LikeDBAccess;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LikeManager implements LikeDataAccess {
    private LikeDataAccess likeDataAccess;

    public LikeManager() throws ConnectionDataAccessException {
        setLikeDAO(new LikeDBAccess());
    }

    public void setLikeDAO(LikeDataAccess likeDataAccess) {
        this.likeDataAccess = likeDataAccess;
    }

    public List<LikeModel> getLikesBetween(Date startDate, Date endDate) throws LikeSearchException {
        if (startDate.after(endDate))
            throw new LikeSearchException("La date de début doit être antérieure à la date de fin.");

        if (endDate.toLocalDate().isAfter(LocalDate.now()))
            throw new LikeSearchException("La date de fin doit être avant demain.");

        return likeDataAccess.getLikesBetween(startDate, endDate);
    }
}
