package fr.tao.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import fr.tao.springbatch.model.BankTransaction;
import lombok.Getter;

//@Component
public class BankTransactionItemAmountsProcessor implements ItemProcessor<BankTransaction, BankTransaction> {
	
	@Getter private double totalDebit;
	@Getter private double totalCredit;
	
	@Override
	public BankTransaction process(BankTransaction bankTransaction) throws Exception {
		if (bankTransaction.getTransactionType().equals("D"))
			totalDebit += bankTransaction.getAmount();
		else if (bankTransaction.getTransactionType().equals("C"))
			totalCredit += bankTransaction.getAmount();
		return bankTransaction;
	}

}
