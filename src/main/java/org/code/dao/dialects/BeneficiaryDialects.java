package org.code.dao.dialects;

public interface BeneficiaryDialects {
	
	String INSERT_BENEFICIARY = "INSERT INTO beneficiaries("
			+ "sender, recipient"
			+ ") VALUES(?,?)";	
	
	String BENEFICIARY_CHECK = "SELECT 1 FROM beneficiaries WHERE sender = ? and recipient = ? LIMIT 1";
	
	String READ_BENEFICIARIES = "SELECT beneficiaries.sender, beneficiaries.recipient, customers.contact FROM beneficiaries JOIN accounts ON accounts.accountNumber = beneficiaries.recipient JOIN customers ON customers.customerID = accounts.customerID  WHERE sender = ?";

}
