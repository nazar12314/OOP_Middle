package com.example.demo.repositories;

import com.example.demo.dto.ResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseDTORepository extends JpaRepository<ResponseDTO, String> {
    Optional<ResponseDTO> findByDomain(String domain);
}
