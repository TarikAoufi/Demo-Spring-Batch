package fr.tao.springbatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tao.springbatch.model.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>{

}