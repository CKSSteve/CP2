package com.uxb2b.ecp.service;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

/**
 * 
 * @author steve
 *
 */

@Service
public class PdfService {
	
	private Logger log = LoggerFactory.getLogger(PdfService.class);
	
	/**
	 * html轉PDF
	 * 
	 * @param htmlBody 欲轉換成PDF的HTML
	 * @param printFmt 要橫印還直印
	 * @return byte[] pdf
	 */
	public byte[] makePdf(String htmlBody, String printFmt) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PD4ML pd4ml = new PD4ML();
        
        htmlBody = htmlBody.replaceAll("新細明體", "MingLiU");
        htmlBody = htmlBody.replaceAll("細明體", "MingLiU");
        htmlBody = htmlBody.replaceAll("標楷體", "DFKai-SB");
        
        pd4ml.enableImgSplit(false);
        pd4ml.disableHyperlinks();
        //調整為一頁
        pd4ml.fitPageVertically();
        
        // width:列印時為橫印
        if ("width".equals(printFmt)) {
            // Inserts(top, left, bottom, right)
        	pd4ml.setPageInsets(new Insets(16, 10, 0, 10));
            Dimension hd = pd4ml.changePageOrientation(new Dimension(PD4Constants.A4));
            pd4ml.setPageSize(hd); //根據內容設定紙張大小
            pd4ml.setHtmlWidth(900); //A3大小設為1350, 若大小約A4時建議值為900
        } else {
        	pd4ml.setPageInsetsMM(new Insets(16, 10, 0, 10));
        	pd4ml.setPageSize(PD4Constants.A4);
        	pd4ml.setHtmlWidth(1000);
        }

        try {
        	pd4ml.useTTF("java:/fonts", true);  //固定設定, 才能使用fonts.jar
        	pd4ml.render(new StringReader(htmlBody), baos);  //轉檔
        } catch (FileNotFoundException e) {
            log.error(e.toString(), e);
        } catch (InvalidParameterException e) {
        	log.error(e.toString(), e);
		} catch (MalformedURLException e) {
			log.error(e.toString(), e);
		} catch (IOException e) {
			log.error(e.toString(), e);
		}
        return baos.toByteArray();
    }
}
