package com.home.practice.productrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.practice.productrest.model.Dealer;
import com.home.practice.productrest.model.DealerAndAddressProjection;
import com.home.practice.productrest.repository.DealerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DealerService {
	
	@Autowired
	private DealerRepository drepo;
	
	public Dealer registerDealer(Dealer d) {
		return drepo.save(d);
	}

	public Optional<Dealer> loginDealer(String email) {

		return drepo.findByEmail(email); // Invoke Custom method
	}
	
	public List<DealerAndAddressProjection> getDealerInfo() {
        return drepo.findSelectedFieldsFromDealerAndAddress(); // Invokes custom query method
    }

	public Boolean authenticateDealer(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
