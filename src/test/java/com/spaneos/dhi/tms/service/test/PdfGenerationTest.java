package com.spaneos.dhi.tms.service.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spaneos.dhi.tms.SeedDataUtil;
import com.spaneos.dhi.tms.dto.UserDTO;

@SpringBootTest
public class PdfGenerationTest {
		@Autowired
		private SeedDataUtil seedDataUtil;
		@Test
		void generatePdf() throws JAXBException, FileNotFoundException {
			 UserDTO userDTO = seedDataUtil.fetchUser();
		    
			 JAXBContext contextObj = JAXBContext.newInstance(UserDTO.class);
		  
		     Marshaller marshallerObj = contextObj.createMarshaller();  
		     
		     marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
		     marshallerObj.marshal(userDTO, new FileOutputStream("user.xml"));
		    
		}

}
