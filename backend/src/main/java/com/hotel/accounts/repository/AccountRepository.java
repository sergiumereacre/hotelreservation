package com.hotel.accounts.repository;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    // Common methods
    Optional<AccountEntity> findByEmail(String email);

    // Staff-specific methods
    List<AccountEntity> findAllByIsStaff(boolean isStaff);
    Optional<AccountEntity> findByIdAndIsStaff(Long id, boolean isStaff);
    Optional<AccountEntity> findByEmailAndIsStaff(String email, boolean isStaff);

    //Guest methods
    Optional<GuestAccountEntity> findByIdAndIsGuest(Long id, boolean isGuest);
    List<GuestAccountEntity> findAllByIsGuest(boolean isGuest);
    Optional<GuestAccountEntity> findByEmailAndIsGuest(String email, boolean isGuest);
}
