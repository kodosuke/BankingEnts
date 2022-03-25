package org.code.dao.dialects;

public interface BeneficiaryDialects {
	
	String INSERT_BENEFICIARY = "INSERT INTO beneficiaries("
			+ "sender, recipient, contact"
			+ ") VALUES(?,?,?)";
	String READ_BENEFICIARIES = "SELECT * FROM beneficiaries WHERE sender = ?";		
	
	String BENEFICIARY_CHECK = "SELECT 1 FROM beneficiaries WHERE sender = ? and recipient = ? LIMIT 1";

}
