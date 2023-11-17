package com.hotel.accounts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    // Staff-specific methods
    List<AccountEntity> findAllByIsStaff(boolean isStaff);
    Optional<AccountEntity> findByIdAndIsStaff(Long id, boolean isStaff);
    Optional<AccountEntity> findByEmailAndIsStaff(String email, boolean isStaff);

    //Guest methods
    @Query("SELECT a FROM AccountEntity a WHERE TYPE(a) = GuestAccountEntity")
    List<GuestAccountEntity> findAllGuests();

    Optional<GuestAccountEntity> findByEmail(String email);
}
