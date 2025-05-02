import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.api.common.functions.MapFunction;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlinkSSRFJob {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> stream = env.fromElements("test");

        stream.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) {
                try {
                    URL url = new URL("http://localhost:8080");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    int responseCode = conn.getResponseCode();
                    System.out.println("success: " + responseCode);
                    return value + " - response: " + responseCode;
                } catch (Exception e) {
                    System.out.println("req failed: " + e.getMessage());
                    return value + " - err: " + e.getMessage();
                }
            }
        }).print();

        env.execute("Flink SSRF Job");
    }
}
