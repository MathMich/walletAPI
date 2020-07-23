package com.wallet.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;





@RunWith(SpringRunner.class)
@SpringBootTest

public class WalletItemsRepositoryTest {

	private static final Date DATE = new Date(); // Pega a data atual
	private static final TypeEnum TYPE = TypeEnum.EN;;
	private static final String DESCRIPTION = "RECEBIMENTO DIV";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	private Long savedWalletItemid = null;
	private Long savedWalletId = null;
	
	@Autowired
    WalletItemRepository repository; //ainda não tem a interface vai refatorar
	@Autowired
	WalletRepository walletRepository;
	
	
	@Before
	public void setUp() {
		Wallet w = new Wallet();
		w.setName("Conta de Luz");
		w.setValue(BigDecimal.valueOf(250));
		
		walletRepository.save(w);
		
		WalletItem wi = new WalletItem(null,w,DATE,TYPE,DESCRIPTION,VALUE);
		repository.save(wi);
		
		savedWalletItemid = wi.getId();
		savedWalletId = w.getId();
	}
	
	@After
	public void tearDown() {
		repository.deleteAll();
		walletRepository.deleteAll();
	}
	
	
	@Test
	public void testSave() {
		
		Wallet w = new Wallet();
		w.setName("Carteira 1");
		w.setValue(BigDecimal.valueOf(100));
		walletRepository.save(w);
		
		
		WalletItem wi = new WalletItem( 1L,w,DATE,TYPE,DESCRIPTION,VALUE); // ainda não tem a classe vai refatorar
		
		WalletItem response = repository.save(wi);
		
		assertNotNull(response);
		
		assertEquals(response.getDescription(), DESCRIPTION); // verifica se response é igual à constante que criamos
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getValue(),VALUE);
		assertEquals(response.getWallet().getId(), w.getId());
		
	}
	
	
	
	@Test(expected = ConstraintViolationException.class)
	public void testSaveInvalidWalletItem() {
		WalletItem wi = new WalletItem(null,null,DATE,null,DESCRIPTION,null);
		repository.save(wi);
	}
	
	@Test
	public void testUpdate() {
		Optional<WalletItem> wi = repository.findById(savedWalletItemid);
		
		String description = "Descrição alterada";
		
		WalletItem changed = wi.get();
		
		changed.setDescription(description);

	    repository.save(changed);
	    
	    Optional<WalletItem> newWalletItem = repository.findById(savedWalletItemid);
	    
	    assertEquals(description,newWalletItem.get().getDescription());
	}
	
	@Test
	public void deleteWalletItem() {
		Optional<Wallet> wallet = walletRepository.findById(savedWalletId);
		WalletItem wi = new WalletItem(null,wallet.get(),DATE,TYPE,DESCRIPTION,VALUE);
		
		repository.save(wi);
		
		repository.deleteById(wi.getId());
		
		// Logo depois tenta buscar o ítem que excluiu
		Optional<WalletItem> response = repository.findById(wi.getId());
		
		// garante que não veio nada
		assertFalse(response.isPresent());
	}
	
	
	
	
	
	
	
}
