package main.businessPackage;

import main.dataAccessPackage.CommunityDataAccess;
import main.dataAccessPackage.CommunityDBAccess;
import main.exceptionPackage.CommunityDAOException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;
import java.util.List;

public class CommunityManager implements CommunityDataAccess {
    private CommunityDataAccess communityDataAccess;

    public CommunityManager() throws ConnectionDataAccessException {
        setCommunityDAO(new CommunityDBAccess());
    }

    public void setCommunityDAO(CommunityDataAccess communityDataAccess) {this.communityDataAccess = communityDataAccess;}

    public List<CommunityModel> getAllCommunities() throws CommunityDAOException {
        return communityDataAccess.getAllCommunities();
    }

    public List<MemberModel> getCommunityById(int id) throws CommunityDAOException {
        return communityDataAccess.getCommunityById(id);
    }
}
