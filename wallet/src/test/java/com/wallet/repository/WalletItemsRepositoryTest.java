package com.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;



@RunWith(SpringRunner.class)
@SpringBootTest

public class WalletItemsRepositoryTest {

	private static final Date DATE = new Date(); // Pega a data atual
	private static final String TYPE = "EN";
	private static final String DESCRIPTION = "RECEBIMENTO DIV";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	
	@Autowired
    WalletItemRepository repository; //ainda não tem a interface vai refatorar
	@Autowired
	WalletRepository walletrepository;
	
	
	
	
	@Test
	public void testSave() {
		
		Wallet w = new Wallet();
		w.setName("Carteira Item");
		w.setValue(BigDecimal.valueOf(100));
		walletrepository.save(w);
		
		
		WalletItem wi = new WalletItem( 1L,w,DATE,TYPE,DESCRIPTION,VALUE); // ainda não tem a classe vai refatorar
		
		WalletItem response = repository.save(wi);
		
		assertNotNull(response);
		
		assertEquals(response.getDescription(), DESCRIPTION); // verifica se response é igual à constante que criamos
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getValue(),VALUE);
		assertEquals(response.getWallet().getId(), w.getId());
		
	}
	
	
	
	
	
	
}
