package com.plag;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.bouncycastle.asn1.cms.MetaData;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;

public class ReadFile {
	
	public void doSomething() throws FileNotFoundException, IOException, SAXException, TikaException{
		
//		XWPFDocument docx = new XWPFDocument(
//				   new FileInputStream("/home/shubham/Downloads/bansal_cv.docx"));
//				   //using XWPFWordExtractor Class
//		XWPFWordExtractor we = new XWPFWordExtractor(docx);
//		
//		System.out.println(we.getText());
		URL url;
		File file = new File("/home/shubham/Downloads/bansal_cv.docx");
		if(file.isFile()){
			url = file.toURI().toURL();
		} else{
			url = new URL("name.docx");
		}
		ParseContext context = null;
		
		OutputStream outstream = new ByteArrayOutputStream();
		Metadata metadata = new Metadata();
		TikaConfig tika = TikaConfig.getDefaultConfig();
		InputStream input = TikaInputStream.get(url, metadata);
		ContentHandler handler = new BodyContentHandler(outstream);
		
		
		Detector detector = new DefaultDetector();
		Parser parser = new AutoDetectParser(detector);
		context.set(Parser.class, parser);
		parser.parse(input, handler, metadata, context);
		
		input.close();
		
		String text = outstream.toString();
		
		System.out.println(text);
		
		
		
	}
}
