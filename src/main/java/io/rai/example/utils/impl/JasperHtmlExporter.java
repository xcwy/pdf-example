package io.rai.example.utils.impl;

import io.rai.example.utils.JasperExport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

public class JasperHtmlExporter extends JasperExport {
	private static final Logger logger = LoggerFactory.getLogger(JasperHtmlExporter.class);
	@Override
	public void prepare(HttpServletResponse response, JasperPrint jasperPrint,
			String filename) throws Exception {
		PrintWriter out=null;
		try {
			response.setContentType("text/html; charset=utf-8");
			out = response.getWriter();
			HtmlExporter htmlExporter = new HtmlExporter();
			htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
			htmlExporter.exportReport();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("JasperHtmlExporter==>prepare异常："+e);
			out.close();
		}
	}
}
