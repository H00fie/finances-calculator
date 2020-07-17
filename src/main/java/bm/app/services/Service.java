package bm.app.services;

import bm.app.config.Constants;
import bm.app.models.FinanceProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static bm.app.config.Constants.*;
import static bm.app.config.Constants.DEFAULT_PASSWORD;

public class Service {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    private Connection getConnection(){
        Connection connection = Constants.getConnection(
                DEFAULT_DRIVER,
                DEFAULT_URL,
                DEFAULT_USERNAME,
                DEFAULT_PASSWORD);
        if (connection == null){
            return null;
        }
        return connection;
    }

    public static BigDecimal increaseByTenPercent(BigDecimal amount){
        BigDecimal divisor = new BigDecimal(10);
        BigDecimal increasedAmount = amount.divide(divisor);
        BigDecimal result = amount.add(increasedAmount);
        return result;
    }

//    public static BigDecimal findAProductPriceByGivenName(String name){
//
//    }

    public List<FinanceProductModel> selectAllRecords(){
        List<FinanceProductModel> recordsList = new ArrayList<>();
        String sql = "select * from finances";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            logger.error("Cannot extract records from the database");
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                FinanceProductModel model = (FinanceProductModel) applicationContext.getBean("model");
                recordsList.add(model
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getBigDecimal("price"))
                        .validityperiod(resultSet.getInt("validityperiod"))
                        .build());
            }
        } catch (SQLException e){
            logger.error("Cannot create a list of records from the database.");
            e.printStackTrace();
        }
        return recordsList;
    }
}
