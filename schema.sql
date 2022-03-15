CREATE TABLE accounts (
	accountNumber INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
	customerName VARCHAR(100) NOT NULL,
	contact VARCHAR(100) NOT NULL UNIQUE,
	dateOfBirth BIGINT NOT NULL,
	gender ENUM('Male', 'Female') NOT NULL,
	password VARCHAR(255) NOT NULL,
	type ENUM('SAVINGS', 'CURRENT') NOT NULL,
	balance FLOAT NOT NULL DEFAULT 0,
	creationTime BIGINT NOT NULL 
);
	
ALTER TABLE accounts AUTO_INCREMENT=786001;

CREATE TABLE beneficiaries(
	sender INT NOT NULL,
	recipient INT NOT NULL,
	contact VARCHAR(100) NOT NULL,
	PRIMARY KEY (sender, recipient),
	FOREIGN KEY (sender) REFERENCES accounts(accountNumber) ON DELETE CASCADE,
	FOREIGN KEY (recipient) REFERENCES accounts(accountNumber) ON DELETE CASCADE
);
	
CREATE TABLE transactions(
    txnHash BIGINT UNIQUE NOT NULL PRIMARY KEY,
    accountNumber INT NOT NULL,
    amount INT NOT NULL,
    mode ENUM('CREDIT', 'DEBIT') NOT NULL,
    creationTime BIGINT NOT NULL,
    FOREIGN KEY (accountNumber) REFERENCES accounts(accountNumber) ON DELETE CASCADE
);