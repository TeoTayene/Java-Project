package main.businessPackage;

import main.dataAccessPackage.DirectMessageDataAccess;
import main.dataAccessPackage.DirectMessageDBAccess;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.DirectMessageException;
import main.modelPackage.DirectMessageModel;

import java.util.List;

public class DirectMessageManager {
    private DirectMessageDataAccess directMessageDataAccess;

    public DirectMessageManager() throws ConnectionDataAccessException {
        setDirectMessageDAO(new DirectMessageDBAccess());
    }

    public List<DirectMessageModel> getDirectMessagesByUserId(int userId) throws DirectMessageException {
       if(directMessageDataAccess.getDirectMessagesByUserId(userId).isEmpty())
           throw new DirectMessageException("Aucun message direct n'a été trouvé pour cette utilisateur.");
        return directMessageDataAccess.getDirectMessagesByUserId(userId);
    }

    public void setDirectMessageDAO(DirectMessageDBAccess directMessageDAO) {
        this.directMessageDataAccess = directMessageDAO;
    }
}
