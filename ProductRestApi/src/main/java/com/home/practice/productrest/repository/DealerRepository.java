package com.home.practice.productrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.home.practice.productrest.model.Dealer;
import com.home.practice.productrest.model.DealerAndAddressProjection;

public interface DealerRepository extends JpaRepository<Dealer, Long> {

	
	public Optional<Dealer> findByEmail(String email);
	
	
	@Query("SELECT new com.home.practice.productrest.model.DealerAndAddressProjection"
		    + "(d.id, d.fname, d.lname, d.phoneNo, "
		    + "d.email, a.street, a.city, a.pincode) "
		    + "FROM Dealer d JOIN d.address a")
		List<DealerAndAddressProjection> findSelectedFieldsFromDealerAndAddress();

}
