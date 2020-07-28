package com.wallet.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {
	
	Page<WalletItem> findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Long wallet,Date init, Date end, Pageable page);

	List<WalletItem> findByWalletIdAndType(Long wallet, TypeEnum type);
	
	// Usando JPQL
	// quando nativeQuery true não passa pelo Hibernate, aí usa sentença Oracle ou diversos
	@Query(value = "select sum(value) from WalletItem wi where wi.wallet.id = :wallet",nativeQuery = false)
	BigDecimal sumByWalletId(@Param("wallet") Long wallet);
}
