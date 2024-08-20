package main.controllerPackage;

import main.businessPackage.LikeManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;

import java.util.List;

public class LikeController {
    private LikeManager likeManager;

    public LikeController() throws ConnectionDataAccessException {
        likeManager = new LikeManager();
    }

    public List<LikeModel> getLikesBetween(java.util.Date startDate, java.util.Date endDate) throws LikeSearchException {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        return likeManager.getLikesBetween(sqlStartDate, sqlEndDate);
        }
    }
