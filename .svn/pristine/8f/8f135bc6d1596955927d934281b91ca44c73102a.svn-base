package com.uxb2b.ecp.web.controller;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.uxb2b.ecp.service.PdfService;
import com.uxb2b.ecp.service.SystemInfo;


@Controller
@RequestMapping(value = "/common")
public class CommonController {
	
	private Logger log = LoggerFactory.getLogger(CommonController.class);
    
	@Autowired
	private PdfService pdfService; 
	
	
	
	
	@RequestMapping(value = "/generatePDF", method = RequestMethod.POST)
	public void generatePDF(String htmlBody, String printFmt, Integer type, HttpServletResponse response) throws IOException{
		log.info(String.format("列印申報書，產生PDF：%s", htmlBody));
        
        byte[] data = pdfService.makePdf(htmlBody, printFmt);
        response.reset();
        // 列印
        if (type == SystemInfo.PRINT_PDF) { 
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=print.pdf");
        } else { // download
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=print.pdf");
        }
    	response.setContentLength(data.length);
    	ServletOutputStream outputStream = response.getOutputStream();
    	outputStream.write(data);
    	outputStream.flush();      
    	//關閉stream資源
    	outputStream.close();
    	outputStream = null;
    }
    
}
