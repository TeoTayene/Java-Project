package main.businessPackage;

import main.dataAccessPackage.CommunityDAO;
import main.dataAccessPackage.CommunityDAOImpl;
import main.exceptionPackage.CommunityDAOException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;
import java.util.List;

public class CommunityManager implements CommunityDAO {
    private CommunityDAO communityDAO;

    public CommunityManager() throws ConnectionDataAccessException {
        setCommunityDAO(new CommunityDAOImpl());
    }

    public void setCommunityDAO(CommunityDAO communityDAO) {this.communityDAO = communityDAO;}

    public List<CommunityModel> getAllCommunities() throws CommunityDAOException {
        return communityDAO.getAllCommunities();
    }

    public List<MemberModel> getCommunityById(int id) throws CommunityDAOException {
        return communityDAO.getCommunityById(id);
    }
}
