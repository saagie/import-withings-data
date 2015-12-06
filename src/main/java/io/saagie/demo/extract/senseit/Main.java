package io.saagie.demo.extract.senseit;


import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.saagie.demo.extract.senseit.dto.Measure;
import io.saagie.demo.extract.senseit.dto.MeasureGroup;
import io.saagie.demo.extract.senseit.dto.Withings;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import java.util.Iterator;


/**
 * Created by youen on 02/12/2015.
 */
public class Main {


   private static final Logger logger = Logger.getLogger(Main.class);

   public static void main(String[] args) throws Exception {
      if (args.length<4) {
         System.out.println("4 args are required :\n\t- userid\n\t- consumerkey\n\t- token\n\t- hdfsmasteruri (8020 port)");
         System.exit(128);
      }
      logger.info("Initializing...");
      String userid=args[0];
      String oauth_comsumerkey=args[1];
      String oauth_token=args[2];
      String uri = args[3];

      Withings w=null;
      logger.info("Calling Withings API...");
      HttpRequest res=HttpRequest.get("http://wbsapi.withings.net/measure?action=getmeas&oauth_consumer_key="+oauth_comsumerkey+"&oauth_nonce=67bac1a37f5f03183c18fe29200f17b1&oauth_signature=2lZby%2FhCfqZXR6V5Qv6LYz9GYmg%3D&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1449047956&oauth_token="+oauth_token+"&oauth_version=1.0&userid="+userid);
      String body=res.body();

      w=new Gson().fromJson(body, new TypeToken<Withings>() { }.getType());
      String measuregrps=new Gson().toJson(w.getBody().measuregrps);


      logger.info("Building CSV...");
      String csv="userid;groupid;creationdate;category;categorydescription;attributionstatus;actualvalue;unit;type\n";

      Iterator<MeasureGroup> it_mg=w.getBody().measuregrps.iterator();
      while(it_mg.hasNext()) {
         MeasureGroup mg=it_mg.next();

         Iterator<Measure> it_m=mg.getMeasures().iterator();
         while(it_m.hasNext()) {
            Measure m=it_m.next();
            String cat_value=null;
            if (mg.getCategory()!=null) {
               cat_value=mg.getCategory().getValue()+"";
            }
            String cat_des=null;
            if (mg.getCategory()!=null) {
               cat_des=mg.getCategory().getDescription()+"";
            }
            String att_status=null;
            if (mg.getAttributionStatus()!=null) {
               att_status=mg.getCategory().getValue()+"";
            }
            csv+=userid+";"+mg.getGroupId()+";"+mg.getCreationDate()+";"+cat_value+";"+cat_des+";"+att_status+";"+m.getActualValue()+";"+m.getUnit()+";"+m.getType()+"\n";
         }
      }

      // Create Directories
      logger.debug("Create directory");
      FileSystem fs = HdfsUtils.getFileSystemFromUri(uri);
      Path directoryraw = HdfsUtils.createDirectory(fs, "/iot/withings/raw");
      Path directorycsv = HdfsUtils.createDirectory(fs, "/iot/withings/csv");


      logger.debug("Create file");
      // Creating a file in HDFS
      HdfsUtils.createJson(fs, directoryraw, w.getBody().updatetime+".json", measuregrps);

      logger.info("Import done of raw file : "+w.getBody().updatetime+".json");

      HdfsUtils.createJson(fs, directorycsv, w.getBody().updatetime+".csv", csv);

      logger.info("Import done of csv file : "+w.getBody().updatetime+".csv");


      logger.info("Done.");


   }
}
