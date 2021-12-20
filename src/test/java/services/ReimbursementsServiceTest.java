package services;

import dao.ReimbursementsDao;
import models.Reimbursements;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReimbursementsServiceTest {

    ReimbursementsDao reimbursementsDao = Mockito.mock(ReimbursementsDao.class);

    ReimbursementsService reimbursementsService;

    public ReimbursementsServiceTest(){this.reimbursementsService = new ReimbursementsService(reimbursementsDao);}

    @Test
    void getAllReimbursements(){

        List<Reimbursements> expectedResult = new ArrayList<>();
        expectedResult.add(new Reimbursements(1, 60.00, "2021-12-20 08:48:38.099", "2021-12-20 08:48:38.099", "Gas expense",
                2,	1, 1, 2));
        expectedResult.add(new Reimbursements(2, 903.28, "2021-12-20 08:48:38.099", "2021-12-20 08:48:38.099", "Room",
                3,	2, 1, 1));
        expectedResult.add(new Reimbursements(3, 191.44, "2021-12-20 08:48:38.099", "2021-12-20 08:48:38.099", "Dinner",
                2,	1, 1, 2));
        expectedResult.add(new Reimbursements(4, 21.39, "2021-12-20 08:48:38.099", "2021-12-20 08:48:38.099", "Wolverine Claws",
                5,	2, 2, 4));
        Mockito.when(reimbursementsDao.getAllReimbursements()).thenReturn(expectedResult);

        List<Reimbursements> actualResult = reimbursementsDao.getAllReimbursements();

        assertEquals(expectedResult.toString(), actualResult.toString());

    }

    @Test
    void getReimbursement(){

        Reimbursements expectedResult = new Reimbursements(1, 60.00, "2021-12-20 08:48:38.099", "2021-12-20 08:48:38.099", "Gas expense",
                2,	1, 1, 2);

        Mockito.when(reimbursementsDao.getReimbursements(expectedResult.getReimb_id())).thenReturn(expectedResult);

        Reimbursements actualResult = reimbursementsService.getReimbursements(expectedResult.getReimb_id());

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void approveReimbursement(){
        Integer reimbId = 1;
        ReimbursementsService.approveReimbursement(reimbId);
        Mockito.verify(reimbursementsDao, Mockito.atLeastOnce()).updateAReimbursement(reimbId);

    }

    @Test
    void deleteClient(){
        Reimbursements reimbursement = new Reimbursements(1, 60.00, "2021-12-20 08:48:38.099", "2021-12-20 08:48:38.099", "Gas expense",
                2,	1, 1, 2);
        reimbursementsService.deleteReimbursement(reimbursement);
        Mockito.verify(reimbursementsDao, Mockito.atLeastOnce()).deleteReimbursement(reimbursement);

    }
}
