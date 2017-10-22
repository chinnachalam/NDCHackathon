
package tcs.ndc.hackathon.ndcrest.service;

import com.pdfcrowd.Pdfcrowd;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Connection;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Offer;
import tcs.ndc.hackathon.ndcrest.model.offer.response.Segment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ImageGeneratorService {

    @Value("${image.logo.defaultPath}")
    private String imageLogoPath;

    @Async
    public String createConnectionImage(String shopId, Connection connection) {
        String imageID = UUID.randomUUID().toString();

        try {
        new File("./ndc-rest/generated/"+shopId).mkdir();
        Path path = Paths.get("./ndc-rest/generated/"+shopId);
        Files.createDirectories(path);
        String imageLinkHQ = "./ndc-rest/generated/" + shopId + "/" + imageID + ".png";
        //String imageLinkLQ = "./ndc-rest/generated/" + shopId +"/" + UUID.randomUUID().toString() +".png";

        List<Segment> segments = connection.getSegments();
        String departureTime = segments.get(0).getDeparture().getTime();
        String arrivalTime = segments.get(segments.size()-1).getArrival().getTime();
        String noOfStops = String.valueOf(segments.size() - 1);
        String originAirportCode = segments.get(0).getDeparture().getAirportCode();
        String destinationAirportCode = segments.get(segments.size()-1).getArrival().getAirportCode();
        List<String> transferAirportList = new ArrayList<>();
        if(segments.size()>1) {
            for (int index = 1; index < segments.size(); index++) {
                transferAirportList.add(segments.get(index).getDeparture().getAirportCode());
            }
        }
        String connectionTime = connection.getConnectionTime();
        String departureDate = segments.get(0).getDeparture().getDate();
        String arrivalDate = segments.get(0).getArrival().getDate();
        String flightNumber = segments.get(0).getMarketingAirline();
        String airlineLogoPath = imageLogoPath + flightNumber.substring(0,2) + ".png";

            Pdfcrowd.HtmlToImageClient client = new Pdfcrowd.HtmlToImageClient("vivekmsv", "80ec699d06907ced9a24492c31c6a0f8");
            client.setOutputFormat("png");
            client.setScreenshotWidth(575);
            client.setScreenshotHeight(340);
            /*File file = new File("af.png");
            System.out.println(file.getAbsolutePath());*/
            client.convertStringToFile("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"card2x\" style=\"width: 566px; height: 320px; border:1px solid #868686; font-family:Arial, sans-serif;\">\n" +
                    "        <div class=\"border\" style=\"width: 566px; height: 4px; background:#245c7c;\"></div>\n" +
                    "        <div class=\"content\" style=\"margin: 20px; width:546px; height:296px;\">\n" +
                    "            <div class=\"row\" style=\"width:526px; display: table;\">\n" +
                    "                <div class=\"col1\" style=\"float: left; text-align: left; width:170px;\">\n" +
                    "                    <div style=\"font-size: 32px; color:#333333; font-family: helvetica; margin-bottom: 15px;\">"+ departureTime +"</div>\n" +
                    "                    <div style=\"font-size: 48px; color:#245c7c; font-family: helvetica;margin-bottom: 15px;\">"+originAirportCode+"</div>\n" +
                    "                    <div style=\"font-size: 24px; color:#838383; font-family: helvetica;\">"+departureDate+"</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"col2\" style=\"float: left; text-align: center; width: 180px;\">\n" +
                    "                    <div style=\"font-size: 28px; color:#838383; font-family: helvetica;margin-bottom: 24px;\">"+noOfStops+"</div>\n" +
                    "                    <div style=\"font-size: 32px; color:#333333; font-family: helvetica;margin-bottom: 26px;\">("+transferAirportList.get(0)+")</div>\n" +
                    "                    <div style=\"font-size: 24px; color:#838383; font-family: helvetica;\">"+connectionTime+"</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"col3\" style=\"float: left; text-align: right; width:170px;\">\n" +
                    "                    <div style=\"font-size: 32px; color:#333333; font-family: helvetica; margin-bottom: 15px;\">"+arrivalTime+"</div>\n" +
                    "                    <div style=\"font-size: 48px; color:#245c7c; font-family: helvetica;margin-bottom: 15px;\">"+destinationAirportCode+"</div>\n" +
                    "                    <div style=\"font-size: 24px; color:#838383; font-family: helvetica;\">"+arrivalDate+"</div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"row\" style=\"widows: 526px; margin-top: 25px; display: table;\">\n" +
                    "                <div style=\"width:260px; float:left;\">\n" +
                    "                    <img src=\"" + airlineLogoPath + "\" style=\"margin-top: 5px;\">\n" +
                    "                </div>\n" +
                    "                <div style=\"width:263px;font-size:28px; color:#838383; font-family: helvetica;color:#245c7c;float:right; text-align: right\">"+flightNumber+"</div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <div class=\"row\" style=\"margin-top: 20px\">\n" +
                    "                <div style=\"font-size: 24px; color:#333333; font-family: helvetica;\">Extras: Meal, Wifi, Inflight Entertainment</div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>\n", imageLinkHQ);

            /*client.convertStringToFile( "    <div class=\"margin\" style=\"margin-bottom: 30px;\"></div>\n" +
                    "    <div class=\"card1x\" style=\"width: 283px; height: 160px; border:1px solid #868686; font-family:Arial, sans-serif;\">\n" +
                    "        <div class=\"border\" style=\"width: 283px; height: 4px; background:#245c7c; \"></div>\n" +
                    "        <div class=\"content\" style=\"margin: 15px; width:253px; height:148px;\">\n" +
                    "            <div class=\"row\" style=\"width:253px; display: table;\">\n" +
                    "                <div class=\"col1\" style=\"float: left; text-align: left; width:80px;\">\n" +
                    "                    <div style=\"font-size: 16px; color:#333333; font-family: helvetica; margin-bottom: 10px;\">09:30</div>\n" +
                    "                    <div style=\"font-size: 24px; color:#245c7c; font-family: helvetica;margin-bottom: 10px;\">AMS</div>\n" +
                    "                    <div style=\"font-size: 12px; color:#838383; font-family: helvetica;\">20 OCT 2017</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"col2\" style=\"float: left; text-align: center; width: 92px;\">\n" +
                    "                    <div style=\"font-size: 14px; color:#838383; font-family: helvetica;margin-bottom: 12px;\">2 stops</div>\n" +
                    "                    <div style=\"font-size: 16px; color:#333333; font-family: helvetica;margin-bottom: 13px;\">(CDG,LAX)</div>\n" +
                    "                    <div style=\"font-size: 12px; color:#838383; font-family: helvetica;\">36H 20M</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"col3\" style=\"float: left; text-align: right; width:80px;\">\n" +
                    "                    <div style=\"font-size: 16px; color:#333333; font-family: helvetica; margin-bottom: 10px;\">13:30</div>\n" +
                    "                    <div style=\"font-size: 24px; color:#245c7c; font-family: helvetica;margin-bottom: 10px;\">SFO</div>\n" +
                    "                    <div style=\"font-size: 12px; color:#838383; font-family: helvetica;\">21 OCT 2017</div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"row\" style=\"widows: 253px; margin-top: 10px; display: table;\">\n" +
                    "                <div style=\"width:126px; float:left;\">\n" +
                    "                    <img src=\"" + "" + "\" style=\"margin-top: 2px;\">\n" +
                    "                </div>\n" +
                    "                <div style=\"width:126px;font-size:14px; color:#838383; font-family: helvetica;color:#245c7c;float:right; text-align: right\">AF124</div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <div class=\"row\"style=\"margin-top: 10px\">\n" +
                    "                <div style=\"font-size: 12px; color:#333333; font-family: helvetica;\">Extras: Meal, Wifi, Inflight Entertainment</div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n", imageLinkLQ);*/

            System.out.println("Connection Image Generated");
        } catch (Pdfcrowd.Error error) {
            System.err.println("Error: " + error);
        } catch (IOException error) {
            System.err.println("Error: " + error.getMessage());
        }
        return imageID;
    }

    /*public static void main(String args[]) {
        new ImageGeneratorService().buildConnectionImageLink(null,null);
    }*/
}