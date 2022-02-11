package com.kokonut.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kokonut.backend.modelos.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
	
	@Query(value = "EXEC SelectAllFotoSP @page = :page , @size = :size", nativeQuery = true)
	List<Foto> selectAllFotos(@Param("page") Integer page, @Param("size") Integer size);
	
	@Query(value = "EXEC SelectByUserFotoSP @page = :page , @size = :size, @email = :email", nativeQuery = true)
	List<Foto> selectFotosByEmail(@Param("page") Integer page, @Param("size") Integer size, @Param("email") String email);
	
	@Procedure("CreateFotoSP")
	void createFoto(@Param("name") String name, @Param("latitude") Double latitude, @Param("longitude") Double longitude,
			@Param("enabled") Boolean enabled, @Param("email") String email);

	@Procedure("DeleteFotoMODSP")
	void deleteFotoMod(@Param("idFoto") Long id);
	
	@Procedure("DeleteFotoUserSP")
	void deleteFotoUser(@Param("idFoto") Long id, @Param("email") String email);
	
}