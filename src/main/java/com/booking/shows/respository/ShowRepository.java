package com.booking.shows.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
}