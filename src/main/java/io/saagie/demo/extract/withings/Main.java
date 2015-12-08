package io.saagie.demo.extract.withings;


import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.saagie.demo.extract.withings.dto.Withings;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;


/**
 * Created by youen
 */

public class Main {
    public static final String WITHINGS_URL = "http://wbsapi.withings.net/measure";
    private static final Logger logger = Logger.getLogger(Main.class);
    public static String CSV = "userid;groupid;creationdate;category;categorydescription;attributionstatus;actualvalue;unit;type\n";

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            System.out.println("4 args are required :\n\t- userid\n\t- consumerkey\n\t- token\n\t- hdfsmasteruri (8020 port)");
            System.exit(128);
        }
        logger.info("Initializing...");
        String userid = args[0];
        String oauth_comsumerkey = args[1];
        String oauth_token = args[2];
        String uri = args[3];

        Withings withings = null;
        logger.info("Calling Withings API...");
        HttpRequest res = HttpRequest.get(WITHINGS_URL + "?action=getmeas&oauth_consumer_key=" + oauth_comsumerkey
                + "&oauth_nonce=67bac1a37f5f03183c18fe29200f17b1&oauth_signature=2lZby%2FhCfqZXR6V5Qv6LYz9GYmg%3D&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1449047956&oauth_token="
                + oauth_token + "&oauth_version=1.0&userid=" + userid);
        String body = res.body();

        withings = new Gson().fromJson(body, new TypeToken<Withings>() {
        }.getType());

        String measuregrps = new Gson().toJson(withings.getBody().getMeasuregrps());

        logger.info("Building CSV...");

        withings.getBody().getMeasuregrps().stream().forEach(measureGroup -> {
            measureGroup.getMeasures().stream().forEach(measure -> {
                String cat_value = null;
                if (measureGroup.getCategory() != null) {
                    cat_value = measureGroup.getCategory().getValue() + "";
                }
                String cat_des = null;
                if (measureGroup.getCategory() != null) {
                    cat_des = measureGroup.getCategory().getDescription() + "";
                }
                String att_status = null;
                if (measureGroup.getAttributionStatus() != null) {
                    att_status = measureGroup.getCategory().getValue() + "";
                }
                CSV += userid + ";" + measureGroup.getGroupId() + ";" + measureGroup.getCreationDate() + ";" + cat_value + ";" + cat_des + ";" + att_status + ";"
                        + measure.getActualValue() + ";" + measure.getUnit() + ";" + measure.getType() + "\n";
            });
        });

        // Create Directories
        logger.debug("Create directory");
        FileSystem fs = HdfsUtils.getFileSystemFromUri(uri);
        Path directoryraw = HdfsUtils.createDirectory(fs, "/iot/withings/raw");
        Path directorycsv = HdfsUtils.createDirectory(fs, "/iot/withings/csv");
        // Creating a file in HDFS
        logger.debug("Create file");
        int updatetime = withings.getBody().getUpdatetime();
        HdfsUtils.createJson(fs, directoryraw, updatetime + ".json", measuregrps);
        logger.info("Import done of raw file : " + updatetime + ".json");
        HdfsUtils.createJson(fs, directorycsv, updatetime + ".csv", CSV);
        logger.info("Import done of csv file : " + updatetime + ".csv");
        logger.info("Done.");
    }
}
