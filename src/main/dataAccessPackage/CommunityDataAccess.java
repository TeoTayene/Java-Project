package main.dataAccessPackage;

import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;
import main.exceptionPackage.CommunityDAOException;
import java.util.List;

public interface CommunityDataAccess {
    public List<CommunityModel> getAllCommunities() throws CommunityDAOException;
    public List<MemberModel> getCommunityById(int id) throws CommunityDAOException;
}
