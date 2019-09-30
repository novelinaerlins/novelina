package novel.uas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import novel.uas.DAO.TabunganDAO;
import novel.uas.model.TabunganModel;

@RestController
@RequestMapping("/bank")
public class TabunganController {
	@Autowired
	TabunganDAO tabunganDAO;
	
	//SAVE
	@PostMapping("/tabungan")
	public TabunganModel SaveTabungan (@Valid @RequestBody TabunganModel tabunganModel) {
		return tabunganDAO.SaveTabungan(tabunganModel);
	}
	
	//UNTUK GET ALL
	@GetMapping("tabungan")
	public List<TabunganModel> getAll(){
		return tabunganDAO.getAllData();
	}
	
	//GET ALL BY ID
	@GetMapping("/getId/{id}")
	public ResponseEntity<TabunganModel> getTabunganById(@PathVariable(value="id")Long id){
		TabunganModel b = tabunganDAO.getFindById(id);
		if(b==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(b);
		}
	}
	
	//GET ALL BY NIK
	@GetMapping("/getNik/{nik}")
	public List<TabunganModel> getTabunganByNik(@PathVariable(value="nik") String nik){
		return tabunganDAO.getFindByNik(nik);
	}
	
	//UPDATE
	@PutMapping("/Update/{id}")
	public ResponseEntity<TabunganModel> updateTabungan(@PathVariable (value="id")Long id,@Valid @RequestBody TabunganModel tabungan){
		TabunganModel b = tabunganDAO.getFindById(id);
		if(b==null) {
			return ResponseEntity.notFound().build();
		}else {
			b.setDebet(b.getSaldo()+b.getKredit()-b.getDebet());
			b.setKredit(tabungan.getKredit());
			b.setSaldo(tabungan.getSaldo());
			
			TabunganModel bb = tabunganDAO.SaveTabungan(b);
			return ResponseEntity.ok().body(bb);
		}
	}
	
	//DELETE
	@DeleteMapping("tabunganDel/{id}")
	public ResponseEntity<TabunganModel> delete(@PathVariable(value="id") Long id){
		TabunganModel t = tabunganDAO.getFindById(id);
		if(t==null) {
			return ResponseEntity.notFound().build();
		}else {
			tabunganDAO.DeleteTabungan(id);
			return ResponseEntity.ok().build();
		}
	}
	
}
