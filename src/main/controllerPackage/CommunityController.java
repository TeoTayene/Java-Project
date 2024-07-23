package main.controllerPackage;

import main.businessPackage.CommunityManager;
import main.exceptionPackage.CommunityDAOException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;

import java.util.List;

public class CommunityController {
    private CommunityManager communityManager;

    public CommunityController() throws ConnectionDataAccessException {
        communityManager = new CommunityManager();
    }

    public List<CommunityModel> getAllCommunities() throws CommunityDAOException {
        return communityManager.getAllCommunities();
    }

    public List<MemberModel> getCommunityById(int id) throws CommunityDAOException {
        return communityManager.getCommunityById(id);
    }
}
