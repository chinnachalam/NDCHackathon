package tcs.ndc.hackathon.ndcrest.service;

import ch.qos.logback.core.util.FileUtil;
/*import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import gui.ava.html.image.generator.HtmlImageGenerator;*/
//import org.apache.commons.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicEditorPaneUI;

/*import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;*/

public class ImageGenerator {

    public static int DEFAULT_IMAGE_WIDTH = 730;
    public static int DEFAULT_IMAGE_HEIGHT = 700;
    /*
    public static void main(String... args) throws IOException {

        String htmlString = "<html>" +
                "<h1>:)</h1>" +
                "Hello World!<br>" +
                "<img src=\"http://img0.gmodules.com/ig/images/igoogle_logo_sm.png\">" +
                "</html>";
        String htmlString = "<html>" +
                "<head>" +
                "<style> " +
                "	body {" +
                "    	background:white;" +
                "    }" +
                "  #roundedCorner {" +
                "      border-radius: 18px;" +
                "      border: 0.5px solid #d3d3d3;" +
                "      width: 180px;" +
                "      height: 260px;    " +
                "  }" +
                "  .blueFont {" +
                "    color: #309EF8;" +
                "    font-family:monospace;" +
                "  }" +
                "  .fontSize1 {" +
                "      font-size:16px;" +
                "  }" +
                "  .fontSize2 {" +
                "      font-size:12px;" +
                "  }" +
                "  .alignLeft {" +
                "  	margin:0px auto;" +
                "  }" +
                "  .flightSearch{" +
                "  	padding: 10px; " +
                "  }" +
                "  .lineSummary{" +
                "    width: 100%;" +
                "	height:30%;" +
                "	border-bottom: 1px solid #d3d3d3;" +
                "    position: relative;" +
                "    }" +
                "  .lineSelect{" +
                "    width: 100%;" +
                "	border-bottom: 1px solid #d3d3d3;" +
                "    position: relative;" +
                "    }" +
                "  .lineMoreDetails{" +
                "    width: 100%;" +
                "    border-bottom: 1px solid #d3d3d3;" +
                "    position: relative;" +
                "  } " +
                "  .selectButton {" +
                "  	width: 100%;" +
                "    text-align: center " +
                "  }" +
                "" +
                "</style>" +
                "</head>" +
                "<body>" +
                "  <div id=\"roundedCorner\">" +
                "	<p class=\"flightSearch blueFont fontSize1 alignLeft\">AMS - MAD</p>" +
                "    <div class=\"lineSummary\"></div>" +
                "    <p class=\"blueFont fontSize2\">AMS to MAD</p>" +
                "    <p class=\" fontSize2\"> 20H10M</p>" +
                "	<div class=\"lineSelect\"></div>" +
                "    <p class=\"blueFont fontSize2 selectButton\">Select this flight</p>" +
                "    <div class=\"lineMoreDetails\"></div>" +
                "    <p class=\"blueFont fontSize2 selectButton\">View more details</p>" +
                "  </div>" +
                "</body>" +
                "</html>";

        JLabel label = new JLabel(htmlString);
        label.setSize(180, 240);

        BufferedImage image = new BufferedImage(
                label.getWidth(), label.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        {
            Graphics g = image.getGraphics();
            g.setColor(Color.BLACK);
            label.paint(g);
            g.dispose();
        }

        // get the byte array of the image (as jpeg)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();

        ImageIO.write(image, "png", new File("test.png"));



        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        //imageGenerator.loadHtml("<b>Hello World!</b> Please goto <a title=\"Goto Google\" href=\"http://www.google.com\">Google</a>.");
        imageGenerator.loadHtml("<html>" +
                "<head>" +
                "<style> " +
                "	body {" +
                "    	background:white;" +
                "    }" +
                "  #roundedCorner {" +
                "      border-radius: 18px;" +
                "      border: 0.5px solid #d3d3d3;" +
                "      width: 180px;" +
                "      height: 260px;    " +
                "  }" +
                "  .blueFont {" +
                "    color: #309EF8;" +
                "    font-family:monospace;" +
                "  }" +
                "  .fontSize1 {" +
                "      font-size:16px;" +
                "  }" +
                "  .fontSize2 {" +
                "      font-size:12px;" +
                "  }" +
                "  .alignLeft {" +
                "  	margin:0px auto;" +
                "  }" +
                "  .flightSearch{" +
                "  	padding: 10px; " +
                "  }" +
                "  .lineSummary{" +
                "    width: 100%;" +
                "	height:30%;" +
                "	border-bottom: 1px solid #d3d3d3;" +
                "    position: relative;" +
                "    }" +
                "  .lineSelect{" +
                "    width: 100%;" +
                "	border-bottom: 1px solid #d3d3d3;" +
                "    position: relative;" +
                "    }" +
                "  .lineMoreDetails{" +
                "    width: 100%;" +
                "    border-bottom: 1px solid #d3d3d3;" +
                "    position: relative;" +
                "  } " +
                "  .selectButton {" +
                "  	width: 100%;" +
                "    text-align: center " +
                "  }" +
                "" +
                "</style>" +
                "</head>" +
                "<body>" +
                "  <div id=\"roundedCorner\">" +
                "	<p class=\"flightSearch blueFont fontSize1 alignLeft\">AMS - MAD</p>" +
                "    <div class=\"lineSummary\"></div>" +
                "    <p class=\"blueFont fontSize2\">AMS to MAD</p>" +
                "    <p class=\" fontSize2\"> 20H10M</p>" +
                "	<div class=\"lineSelect\"></div>" +
                "    <p class=\"blueFont fontSize2 selectButton\">Select this flight</p>" +
                "    <div class=\"lineMoreDetails\"></div>" +
                "    <p class=\"blueFont fontSize2 selectButton\">View more details</p>" +
                "  </div>" +
                "</body>" +
                "</html>");

        imageGenerator.saveAsImage("hello-world.png");
        imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");


    }*/


    public static boolean paintPage(Graphics g, int hPage, int pageIndex,
                                    JTextPane panel) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = ((BasicEditorPaneUI) panel.getUI())
                .getPreferredSize(panel);
        double panelHeight = d.height;
        double pageHeight = hPage;
        int totalNumPages = (int) Math.ceil(panelHeight / pageHeight);
        g2.translate(0f, -(pageIndex - 1) * pageHeight);
        panel.paint(g2);
        boolean ret = true;

        if (pageIndex >= totalNumPages) {
            ret = false;
            return ret;
        }
        return ret;
    }


    public static void main(String[] args) throws Exception {

    }
}


