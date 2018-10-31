package com.apap.tutorial7.service;
import java.util.List;
import java.util.Optional;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.repository.DealerDb;

@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id){
		return dealerDb.findById(id);
	}
	
	@Override
	public DealerModel addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
		return dealer;
	}
	
	@Override
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}
	
	@Override
	public void updateDealer(Long id, DealerModel newDealer) {
		DealerModel updated = dealerDb.getOne(id);
		updated.setAlamat(newDealer.getAlamat());
		updated.setNoTelp(newDealer.getNoTelp());
		dealerDb.save(updated);
	}
	
	@Override
	public List<DealerModel> viewAllDealer(){
		return dealerDb.findAll();
	}

}
