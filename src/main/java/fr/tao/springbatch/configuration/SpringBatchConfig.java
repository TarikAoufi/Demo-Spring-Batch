package fr.tao.springbatch.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.tao.springbatch.model.BankTransaction;
import fr.tao.springbatch.processor.BankTransactionItemAmountsProcessor;
import fr.tao.springbatch.processor.BankTransactionItemProcessor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired private JobBuilderFactory jobBuilderFactory;	
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	@Bean(name = "data-load-step")
	protected Step step1(ItemReader<BankTransaction> reader, ItemWriter<BankTransaction> writer) {
		return stepBuilderFactory.get("data-load-step")
				.<BankTransaction, BankTransaction>chunk(100)
				.reader(reader)
				.processor(compositeItemProcessor()) 
				.writer(writer)
				.build(); 
	}
	
	@Bean(name = "data-load-job")
	public Job bankJob(@Qualifier("data-load-step") Step step1) {
		return jobBuilderFactory.get("data-load-job")
				.start(step1).build();
	}

	@Bean
	public ItemProcessor<BankTransaction, BankTransaction> compositeItemProcessor() {
		List<ItemProcessor<BankTransaction, BankTransaction>> itemProcessors = new ArrayList<>();
		itemProcessors.add(itemProcessor1());
		itemProcessors.add(itemProcessor2());

		CompositeItemProcessor<BankTransaction, BankTransaction> compositeItemProcessor = new CompositeItemProcessor<>();
		compositeItemProcessor.setDelegates(itemProcessors);
		return compositeItemProcessor;
	}
	
	@Bean
	public BankTransactionItemProcessor itemProcessor1() {		
		return new BankTransactionItemProcessor();
	}
	
	@Bean
	public BankTransactionItemAmountsProcessor itemProcessor2() {		
		return new BankTransactionItemAmountsProcessor();
	}

}
