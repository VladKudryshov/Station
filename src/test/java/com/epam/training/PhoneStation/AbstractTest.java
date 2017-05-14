package com.epam.training.PhoneStation;


import com.epam.training.PhoneStation.conf.ColumnSensingReplacementDataSetLoader;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-root-context.xml")
@Transactional
@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection",
        dataSetLoader = ColumnSensingReplacementDataSetLoader.class)
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class} )
abstract class AbstractTest {
    @Autowired
    protected SessionFactory sessionFactory;
}
