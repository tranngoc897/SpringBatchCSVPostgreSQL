package com.javasampleapproach.batchcsvpostgresql.config;

import com.javasampleapproach.batchcsvpostgresql.task.MyTaskOne;
import com.javasampleapproach.batchcsvpostgresql.task.MyTaskTwo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javasampleapproach.batchcsvpostgresql.dao.CustomerDao;
import com.javasampleapproach.batchcsvpostgresql.model.Customer;
import com.javasampleapproach.batchcsvpostgresql.step.Listener;
import com.javasampleapproach.batchcsvpostgresql.step.Processor;
import com.javasampleapproach.batchcsvpostgresql.step.Reader;
import com.javasampleapproach.batchcsvpostgresql.step.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public CustomerDao customerDao;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer())
				.listener(new Listener(customerDao))
				.flow(step1())
				.start(stepOne()).next(stepTwo())
				.end()
				.build();
	}

	//Step 1
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Customer, Customer>chunk(2).reader(Reader.reader("customer-data.csv"))
				.processor(new Processor()).writer(new Writer(customerDao)).build();

	}

	//Step 2
	@Bean
	public Step stepOne(){
		return stepBuilderFactory.get("stepOne")
				.tasklet(new MyTaskOne())
				.build();
	}

	@Bean
	public Step stepTwo(){
		return stepBuilderFactory.get("stepTwo")
				.tasklet(new MyTaskTwo())
				.build();

	}


}
