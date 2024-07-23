package main.dataAccessPackage;

import main.exceptionPackage.DirectMessageException;
import main.modelPackage.DirectMessageModel;

import java.util.List;

public interface DirectMessageDAO {
    public List<DirectMessageModel> getDirectMessagesByUserId(int userId) throws DirectMessageException;
}
