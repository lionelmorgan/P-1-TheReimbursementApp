package services;

import dao.ReimbursementsDao;
import dao.UsersDao;
import models.Reimbursements;
import models.Users;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersServiceTest {

    UsersDao usersDao = Mockito.mock(UsersDao.class);

    UsersService usersService;

    public UsersServiceTest(){this.usersService = new UsersService(usersDao);}

    @Test
    void getAllUsers(){
        List<Users> expectedResult = new ArrayList<>();
        expectedResult.add(new Users(1, "cbailey24", "cb24pick", "Champ", "Bailey", "cbailey24@dbroncos.com",1));
        expectedResult.add(new Users(2, "pmanning18", "m@nn!ng18", "Peyton", "Manning", "pmanning18@dbroncos.com",2));
        expectedResult.add(new Users(3,"vmiller55", "m!ll3rm@n", "Von", "Miller", "vmiller55@dbroncos.com",3));
        expectedResult.add(new Users(4, "dsanders21", "pr!m3t!m3", "Deion", "Sanders", "dsanders21@dbroncos.com",4));
        expectedResult.add(new Users(5, "bdawkins20", "w0lv3r!n3", "Brian", "Dawkins", "bdawkins20@dbroncos.com",5));
        expectedResult.add(new Users(10, "dthomas88", "dtbronco88", "Demaryius", "Thomas", "dthomas88@dbroncos.com",3));
        Mockito.when(usersDao.getAllUsers()).thenReturn(expectedResult);

        List<Users> actualResult = usersDao.getAllUsers();

        assertEquals(expectedResult.toString(), actualResult.toString());

    }

    @Test
    void getUser(){

        Users expectedResult = new Users(1, "cbailey24", "cb24pick", "Champ", "Bailey", "cbailey24@dbroncos.com",1);

        Mockito.when(usersDao.getUser(expectedResult.getUser_id())).thenReturn(expectedResult);

        Users actualResult = usersService.getUser(expectedResult.getUser_id());

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void createUser(){
        Users userId = new Users(1, "cbailey24", "cb24pick", "Champ", "Bailey", "cbailey24@dbroncos.com",1);;
        UsersService.createUser(userId);
        Mockito.verify(usersDao, Mockito.atLeastOnce()).createUser(userId);

    }


}
