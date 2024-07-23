package main.businessPackage;

import main.dataAccessPackage.DirectMessageDAO;
import main.dataAccessPackage.DirectMessageDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.DirectMessageException;
import main.modelPackage.DirectMessageModel;

import java.util.List;

public class DirectMessageManager {
    private DirectMessageDAO directMessageDAO;

    public DirectMessageManager() throws ConnectionDataAccessException {
        setDirectMessageDAO(new DirectMessageDAOImpl());
    }

    public List<DirectMessageModel> getDirectMessagesByUserId(int userId) throws DirectMessageException {
        return directMessageDAO.getDirectMessagesByUserId(userId);
    }

    public void setDirectMessageDAO(DirectMessageDAOImpl directMessageDAO) {
        this.directMessageDAO = directMessageDAO;
    }
}
