package com.capgemini.service;

import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	@Override
	public Account depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException
	{
		Account account = new Account();
		String acctNo= String.valueOf(accountNumber);
		if (acctNo.length()<4 || acctNo.length()>4){
			throw new InvalidAccountNumberException();
		}
		int intialBalance=100;
		int newBalance= intialBalance+amount;
		account.setAmount(newBalance);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}

}
