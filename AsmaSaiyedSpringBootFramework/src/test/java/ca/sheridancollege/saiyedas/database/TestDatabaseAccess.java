package ca.sheridancollege.saiyedas.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.saiyedas.beans.Capstone;
import ca.sheridancollege.saiyedas.database.DatabaseAccess;

@SpringBootTest
@AutoConfigureTestDatabase
public class TestDatabaseAccess {
	 @Autowired
	    private DatabaseAccess da;
	    
	    @Test
	    public void whenSaveCapstone_getCapstoneListIsOneGreater(){
	       //setup
	        int size = da.getCapstoneList().size();
	        Capstone capstone = new Capstone();
	        capstone.setName("Asma");
	        
	        //when
	        da.save(capstone);
	        
	        //then(the actual test!)
	        assertEquals(da.getCapstoneList().size(), size + 1);
	    }
	}