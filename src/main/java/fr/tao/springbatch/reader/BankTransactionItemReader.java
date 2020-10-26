package fr.tao.springbatch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import fr.tao.springbatch.model.BankTransaction;

@Component
public class BankTransactionItemReader {
	
	@Value("${inputFile}")
	private Resource inputCsv;

	@Bean
	public FlatFileItemReader<BankTransaction> getItemReader() {
		FlatFileItemReader<BankTransaction> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setName("FFIR1");
		fileItemReader.setLinesToSkip(1);
		fileItemReader.setResource(inputCsv);
		fileItemReader.setLineMapper(lineMapper());
		return fileItemReader;
	}
	
	@Bean
	public LineMapper<BankTransaction> lineMapper() {
		DefaultLineMapper<BankTransaction> lineMapper = new DefaultLineMapper<>();		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id", "accountID", "strTransactionDate", "transactionType", "amount");
		lineMapper.setLineTokenizer(lineTokenizer);
		
		BeanWrapperFieldSetMapper<BankTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(BankTransaction.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}

}
