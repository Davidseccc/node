package cz.uhk.secda1.node01.service.DAO;

import cz.uhk.secda1.node01.model.CPU;

public class CPUDAO {

    
    public void insertValue(CPU cpu) throws Exception {
        MySQLValueDAO valueDAO = new MySQLValueDAO();
        
        valueDAO.insertValue(cpu.getValue(), cpu.getCpusensor_ID());
       

    }
   

}
