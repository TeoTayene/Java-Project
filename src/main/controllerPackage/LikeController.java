package main.controllerPackage;

import main.businessPackage.LikeManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;

import java.sql.Date;
import java.util.List;

public class LikeController {
    private LikeManager likeManager;

    public LikeController() throws ConnectionDataAccessException {
        likeManager = new LikeManager();
    }

    public List<LikeModel> getLikesBetween(Date startDate, Date endDate) throws LikeSearchException {
        return likeManager.getLikesBetween(startDate, endDate);
    }
}
