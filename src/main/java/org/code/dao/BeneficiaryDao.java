package org.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.code.dao.dialects.BeneficiaryDialects;
import org.code.models.Beneficiary;
import org.code.utils.DBConnection;

public class BeneficiaryDao implements BeneficiaryDialects{
	
	public static void insertBeneficiary(int sender, int recipient) throws ClassNotFoundException, SQLException {
		
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BENEFICIARY);
		preparedStatement.setInt(1, sender);
		preparedStatement.setInt(2, recipient);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();

	}
	
	public static ArrayList<Beneficiary> getBeneficiaries(int sender) throws ClassNotFoundException, SQLException {
		
		ArrayList<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(READ_BENEFICIARIES);
		preparedStatement.setInt(1, sender);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			
			Beneficiary beneficiary = new Beneficiary();
			beneficiary.setSender(sender);
			beneficiary.setRecipient(resultSet.getInt("recipient"));
			beneficiary.setRecipientContact(resultSet.getString("contact"));
			beneficiaries.add(beneficiary);
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
		
		return beneficiaries;
	}

	public static boolean checkIfPresent(int sender, int recipient) throws ClassNotFoundException, SQLException {
		
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(BENEFICIARY_CHECK);
        preparedStatement.setInt(1, sender);
        preparedStatement.setInt(2, recipient);
        ResultSet resultSet = preparedStatement.executeQuery();
		
        int returned = 0;
        while( resultSet.next()){
        	returned = resultSet.getInt(1);
    	}
        return returned == 1;
	 }
	
}
