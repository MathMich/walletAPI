package com.wallet.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.service.WalletItemService;


public class WalletItemServiceImpl implements WalletItemService{

	
    @Autowired
    WalletItemRepository repository;
    
    // Busca no application.propertier lá criou items_per_page
    @Value("${pagination.items_per_page}")
    private int itemsPerPage;
    
    @Override
	public WalletItem save(WalletItem i) {
		return repository.save(i);
	}

	@Override
	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page) {
		
		
		//PageRequest pg = new PageRequest(page,itemsPerPage, null); // Esse não funcionou
		Pageable pg = PageRequest.of(page,itemsPerPage);
		
		
		return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet,start,end,pg);
	}

	
	
}
