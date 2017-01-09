package com.plag;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL; 

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

class TextExtractor { 
    private OutputStream outputstream;
    private ParseContext context;
    private Detector detector;
    private Parser parser;
    private Metadata metadata;
    private String extractedText;

    public TextExtractor() {
        context = new ParseContext();
        detector = new DefaultDetector();
        parser = new AutoDetectParser(detector);
        context.set(Parser.class, parser);
        outputstream = new ByteArrayOutputStream();
        metadata = new Metadata();
    }

    public String process(String filename) throws Exception {
        URL url;
        File file = new File(filename);
        if (file.isFile()) {
            url = file.toURI().toURL();
        } else {
            url = new URL(filename);
        }
        String extension = getFileType(filename);
        if(extension.equals("pdf")){
//        	System.out.println("PDF");
        	BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(new File(filename));
            ParseContext pcontext = new ParseContext();
            
            //parsing the document using PDF parser
            PDFParser pdfparser = new PDFParser(); 
            pdfparser.parse(inputstream, handler, metadata,pcontext);
            return handler.toString();
        } else if(extension.equals("txt")){
        	BufferedReader in = new BufferedReader(new FileReader(filename));
            try{
            	StringBuilder sb = new StringBuilder();
            	String line = in.readLine();
            	while(line!=null){
            		sb.append(line + " ");
            		line = in.readLine();
            	}
            	String ans = sb.toString();
            	return ans;
            } finally{
            	in.close();
            }
        } else{
//      	System.out.println("NOT PDF");
        	InputStream input = TikaInputStream.get(url, metadata);
            ContentHandler handler = new BodyContentHandler(outputstream);
            parser.parse(input, handler, metadata, context); 
            input.close();
            return outputstream.toString();
        }
        
        
    }
    
    public String getFileType(String filename){
    	String extension = "";

    	int i = filename.lastIndexOf('.');
    	if (i > 0) {
    	    extension = filename.substring(i+1);
    	}
    	return extension;
    }

    public String getString() {
        //Get the text into a String object
        extractedText = outputstream.toString();
        //Do whatever you want with this String object.
//        System.out.println(extractedText);
        
        return extractedText;
    }

//    public static void main(String args[]) throws Exception {
//        if (true) {
//            TextExtractor textExtractor = new TextExtractor();
//            textExtractor.process("/home/shubham/Downloads/placement.pptx");
//            String text = textExtractor.getString();
//            System.out.println(text);
//        } 
//    }
}
