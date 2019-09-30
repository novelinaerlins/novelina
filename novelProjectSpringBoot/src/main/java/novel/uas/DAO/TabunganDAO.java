package novel.uas.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import novel.uas.model.TabunganModel;
import novel.uas.repository.TabunganRepository;

@Service
public class TabunganDAO {
	@Autowired
	TabunganRepository tabunganRepository;
	
	//CREATE
	public TabunganModel SaveTabungan(TabunganModel tabunganModel) {
		TabunganModel tabmod=tabunganModel;
		TabunganModel tm=tabunganRepository.findSaldoTabungan(tabunganModel.getNik());
		if(tm==null) {
			tabmod.setSaldo(0-tabmod.getKredit()+tabmod.getDebet());
			return tabunganRepository.save(tabmod);
		}else {
			tabmod.setSaldo(tm.getSaldo()-tabmod.getKredit()+ tabmod.getDebet());
			return tabunganRepository.save(tabmod);
		}
		
	}
	
	//READ ALL
	public List<TabunganModel> getAllData() {
		return tabunganRepository.findAll();
	}
	//READ BY ID
	public TabunganModel getFindById(long id) {
		return tabunganRepository.findOne(id);
		

	}
	//READ BY NIK
	public  List<TabunganModel> getFindByNik(String nik){
		return tabunganRepository.findTabunganByNik(nik);
	}
	
	//UPDATE
	public TabunganModel Update(TabunganModel tabu) {
		TabunganModel tabTamp=tabu;
		TabunganModel tampSaldo=tabunganRepository.findOne(tabTamp.getId());
		tampSaldo.setSaldo(tampSaldo.getSaldo()-tabTamp.getKredit()+tabTamp.getDebet());
		tampSaldo.setDebet(tabTamp.getDebet());
		tampSaldo.setKredit(tabTamp.getKredit());
		int tamp=tampSaldo.getSaldo();
		List<TabunganModel> dataList=tabunganRepository.findTabunganByNik(tabTamp.getNik());
		for(TabunganModel data : dataList) {
			if(data.getId() > tabTamp.getId()) {
				TabunganModel tamp1=tabunganRepository.findOne(data.getId());
				tamp1.setSaldo(tamp-tamp1.getKredit()+tamp1.getDebet());
				tabunganRepository.save(tamp1);
				tamp=tamp1.getSaldo();
			}
		}
		return tabunganRepository.save(tampSaldo);
	}
	
	//DELETE
	public void DeleteTabungan(Long id) {
		TabunganModel tampNik = tabunganRepository.findOne(id);
		List<TabunganModel> dataList = tabunganRepository.findTabunganByNik(tampNik.getNik());
		for(TabunganModel data : dataList) {
			if(data.getId() > id) {
				TabunganModel tamp1 = tabunganRepository.findOne(data.getId());
				tamp1.setSaldo(tamp1.getSaldo()+tampNik.getKredit()-tampNik.getDebet());
				tabunganRepository.save(tampNik);
			}
		}
		tabunganRepository.delete(id);;
	}
	
}
