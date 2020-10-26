package fr.tao.springbatch.processor;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;

import fr.tao.springbatch.model.BankTransaction;

//@Component
public class BankTransactionItemProcessor implements ItemProcessor<BankTransaction, BankTransaction> {
	
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
	
	@Override
	public BankTransaction process(BankTransaction bankTransaction) throws Exception {
		bankTransaction.setTransactionDate(df.parse(bankTransaction.getStrTransactionDate()));
		return bankTransaction;
	}

}
