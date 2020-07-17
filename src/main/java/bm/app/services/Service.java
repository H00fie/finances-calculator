package bm.app.services;

import bm.app.config.Constants;
import bm.app.models.FinanceProductModelDTO;
import bm.app.models.RiskLevel;
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
import static bm.app.models.RiskLevel.*;


@org.springframework.stereotype.Service
public class Service {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    private static Connection getConnection() {
        Connection connection = Constants.getConnection(
                DEFAULT_DRIVER,
                DEFAULT_URL,
                DEFAULT_USERNAME,
                DEFAULT_PASSWORD);
        if (connection == null) {
            return null;
        }
        return connection;
    }

    public static BigDecimal increaseByTenPercent(BigDecimal amount) {
        BigDecimal divisor = new BigDecimal(10);
        BigDecimal increasedAmount = amount.divide(divisor);
        BigDecimal result = amount.add(increasedAmount);
        return result;
    }

    public static RiskLevel giveTheRiskNameBasedOnNumberOfMonths(int months) {
        int riskLevel = calculateRiskLevelByNumberOfMonths(months);
        switch (riskLevel) {
            case 1:
                return NO_RISK;
            case 2:
                return VERY_LOW;
            case 3:
                return LOW;
            case 4:
                return REGULAR;
            case 5:
                return MEDIUM;
            case 6:
                return HIGH;
            case 7:
                return VERY_HIGH;
        }
        return UNSPECIFIED;
    }

    public static int calculateRiskLevelByNumberOfMonths(int months) {
        int riskPointer = 0;
        if (months == 1) {
            riskPointer = 1;
        } else if (months > 1 && months <= 3) {
            riskPointer = 2;
        } else if (months > 3 && months <= 6) {
            riskPointer = 3;
        } else if (months > 6 && months <= 12) {
            riskPointer = 4;
        } else if (months > 12 && months <= 24) {
            riskPointer = 5;
        } else if (months > 24 && months <= 48) {
            riskPointer = 6;
        } else {
            riskPointer = 7;
        }
        return riskPointer;
    }

    public static BigDecimal findAProductPriceByGivenName(String name) {
        BigDecimal result = selectAllRecords()
                .stream()
                .filter(e -> e.equals(name))
                .findFirst()
                .get()
                .getPrice();
        return result;
    }

    public static void insertARecord(FinanceProductModelDTO financeProductModel) {
        Thread insertingThread = new Thread(() -> {
            String sql = "insert into finances (name, price, validityperiod) values(?, ?, ?)";
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, financeProductModel.getName());
                preparedStatement.setBigDecimal(2, financeProductModel.getPrice());
                preparedStatement.setInt(3, financeProductModel.getValidityperiod());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Cannot insert a record to the database.");
                e.printStackTrace();
            }
        });
        insertingThread.start();
    }

    public static void deleteARecord(int id) {
        Thread deletingThread = new Thread(() -> {
            String sql = "delete from finances where id = ?";
            try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Cannot delete the record.");
                e.printStackTrace();
            }
        });
        deletingThread.start();
    }

    public static FinanceProductModelDTO getARecordById(int id) {

        FinanceProductModelDTO model = new FinanceProductModelDTO();
        String sql = "select name, price, validityperiod from finances where id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            model.setId(id);
            model.setName(resultSet.getString("name"));
            model.setPrice(resultSet.getBigDecimal("price"));
            model.setValidityperiod(resultSet.getInt("validityperiod"));

        } catch (SQLException e) {
            logger.error("Could not find the record.");
            e.printStackTrace();
        }

        return model;
    }

    public static List<FinanceProductModelDTO> selectAllRecords() {
        List<FinanceProductModelDTO> recordsList = new ArrayList<>();
        String sql = "select * from finances";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("Cannot extract records from the database");
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                recordsList.add(
                        FinanceProductModelDTO
                                .builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .price(resultSet.getBigDecimal("price"))
                                .validityperiod(resultSet.getInt("validityperiod"))
                                .build());
            }
        } catch (SQLException e) {
            logger.error("Cannot create a list of records from the database.");
            e.printStackTrace();
        }
        return recordsList;
    }
}
