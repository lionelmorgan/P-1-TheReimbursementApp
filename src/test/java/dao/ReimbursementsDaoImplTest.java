package dao;

import models.Reimbursements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.eclipse.jetty.util.StringUtil.getBytes;
import static org.junit.jupiter.api.Assertions.*;

public class ReimbursementsDaoImplTest {

    ReimbursementsDao reimbursementsDao;

    public ReimbursementsDaoImplTest(){
        this.reimbursementsDao = new ReimbursementsDaoImpl(H2Util.url, H2Util.username, H2Util.password);
    }

    @BeforeEach
    void setUp(){ H2Util.createTable();}

    @Test
    void getAllReimbursementsIT(){
        List<Reimbursements> expectedResult = new ArrayList<>();
        expectedResult.add(new Reimbursements(1, 60.00, "2021-12-19 18:41:21.137", "2021-12-19 18:41:21.137", "Gas expense",
                 2,	1, 1, 2));
        expectedResult.add(new Reimbursements(2, 903.28, "2021-12-19 18:41:21.137", "2021-12-19 18:41:21.137", "Room",
                3,	2, 1, 1));
        expectedResult.add(new Reimbursements(3, 191.44, "2021-12-19 18:41:21.137", "2021-12-19 18:41:21.137", "Dinner",
                2,	1, 1, 2));
        expectedResult.add(new Reimbursements(4, 21.39, "2021-12-19 18:41:21.137", "2021-12-19 18:41:21.137", "Wolverine Claws",
                5,	2, 2, 4));

        reimbursementsDao.createReimbursement(expectedResult.get(0));
        reimbursementsDao.createReimbursement(expectedResult.get(1));
        reimbursementsDao.createReimbursement(expectedResult.get(2));
        reimbursementsDao.createReimbursement(expectedResult.get(3));

        List<Reimbursements> actualResult = reimbursementsDao.getAllReimbursements();

        assertEquals(expectedResult.toString(), actualResult.toString());
    }


    @AfterEach
    void tearDown(){H2Util.dropTable();}


}
