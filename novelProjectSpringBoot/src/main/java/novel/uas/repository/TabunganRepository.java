package novel.uas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import novel.uas.model.TabunganModel;

public interface TabunganRepository extends JpaRepository<TabunganModel, Long>{
	
	@Query(value="SELECT * FROM tabungan_tbl WHERE nik=:nik",nativeQuery=true)
	List<TabunganModel> findTabunganByNik(@Param("nik") String nik);
	
	@Query(value="SELECT * FROM tabungan_tbl WHERE nik=:nik ORDER BY id DESC LIMIT 1",nativeQuery=true)
	TabunganModel findSaldoTabungan(@Param("nik") String nik);
}


