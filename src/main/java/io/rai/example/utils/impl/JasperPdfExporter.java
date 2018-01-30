package io.rai.example.utils.impl;

import io.rai.example.utils.JasperExport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperPdfExporter extends JasperExport {
	private static final Logger logger = LoggerFactory.getLogger(JasperPdfExporter.class);
	@Override
	public void prepare(HttpServletResponse response, JasperPrint jasperPrint,
			String filename) throws Exception {
		ServletOutputStream out = null;
		try {
			response.setContentType("application/pdf; charset=utf-8");
			response.setHeader("Content-Disposition", "inline;filename=" + filename
					+ ".pdf");
			
			out = response.getOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			exporter.exportReport();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("JasperPdfExporter==>prepare异常："+e);
		}finally{
			out.close();
		}
		
		
	}

}
