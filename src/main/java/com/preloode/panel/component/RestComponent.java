package com.preloode.panel.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.HttpMethod;
import com.preloode.panel.enumeration.global.RestSource;
import com.preloode.panel.model.global.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class RestComponent {


    private static final Logger logger = LoggerFactory.getLogger(RestComponent.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private EncryptionComponent encryption;


    private String generateBinanceSigned(List<Map<String, Object>> dataList) {

        String result = queryBuilder(dataList) + "&timestamp=" + System.currentTimeMillis();

        result += "&signature=" + encryption.hmacSha256Hash(global.getApi().getBinance().getSecretKey(), result);

        return result;

    }


    private HttpHeader generateBittrexSigned(String url, HttpMethod method, HttpHeader header, List<Map<String, Object>> dataList) {

        HttpHeader result = header;

        result.getName().add("Api-Content-Hash");
        result.getValue().add(encryption.sha512Hash(queryBuilder(dataList)));

        String signature = encryption.hmacSha512Hash(global.getApi().getBittrex().getSecretKey(), header.getValue().get(1) + url + method.toString() + header.getValue().get(2));

        result.getName().add("Api-Signature");
        result.getValue().add(signature);

        return result;

    }


    private HttpHeader generateKrakenPrivate(String path, HttpHeader header, List<Map<String, Object>> dataList) {

        HttpHeader result = header;

        byte[] sha256Byte = encryption.sha256Byte(dataList.get((dataList.size() - 1)).get("nonce") + queryBuilder(dataList));
        byte[] pathByte = path.getBytes(Charset.forName("UTF-8"));

        byte[] concat = new byte[pathByte.length + sha256Byte.length];

        for(int i = 0; i < concat.length; i++) {

            concat[i] = i < pathByte.length ? pathByte[i] : sha256Byte[i - pathByte.length];

        }

        byte[] secretKeyByte = Base64.getDecoder().decode(global.getApi().getKraken().getPrivateKey());

        String sign = "";

        try {

            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(new SecretKeySpec(secretKeyByte, "HmacSHA512"));
            sign = Base64.getEncoder().encodeToString(mac.doFinal(concat));

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        result.getName().add("API-Sign");
        result.getValue().add(sign);

        return result;

    }


    public String queryBuilder(List<Map<String, Object>> dataList) {

        String result = "";

        if(!dataList.isEmpty()) {

            for(Map<String, Object> data : dataList) {

                for(Map.Entry<String, Object> map : data.entrySet()) {

                    if(map.getValue() instanceof Map<?, ?>) {

                        try {

                            ObjectMapper objectMapper = new ObjectMapper();
                            String value = objectMapper.writeValueAsString(data);
                            result += "&" + map.getKey() + "=" + value;

                        } catch(Exception exception) {

                            logger.error(exception.getMessage());

                        }

                    } else {

                        result += "&" + map.getKey() + "=" + map.getValue();

                    }

                }

            }

            result = result.replaceFirst("&", "");

        }

        return result;

    }


    public <T> T send(RestSource source, String baseUrl, String path, HttpMethod method, HttpHeader header, List<Map<String, Object>> dataList) {

        T result = null;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder request = HttpRequest.newBuilder();

        String query = queryBuilder(dataList);

        if(source == RestSource.BinanceSigned) {

            query = generateBinanceSigned(dataList);

        } else if(source == RestSource.BittrexSigned) {

            header = generateBittrexSigned((baseUrl + path), method, header, dataList);

        }

        for(int i = 0; i < header.getName().size(); i++) {

            request.setHeader(header.getName().get(i), header.getValue().get(i).toString());

        }

        if(method == HttpMethod.GET) {

            request.GET();

        } else if(method == HttpMethod.POST) {

            request.POST(HttpRequest.BodyPublishers.noBody());

        }

        URI url = URI.create(baseUrl + path + "?" + query);

        request.uri(url);

        try {

            logger.info("Request to : " + baseUrl + path + " with parameter : " + query);

            HttpResponse<String> httpResponse = client.send(request.build(), HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(httpResponse.body(), new TypeReference<>() {
            });

            logger.info("Response from : " + baseUrl + path + " with parameter : " + httpResponse.body());

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public <T> T sendHttps(RestSource source, String baseUrl, String path, HttpMethod method, HttpHeader header, List<Map<String, Object>> dataList) {

        T result = null;

        if(source == RestSource.KrakenPrivate) {

            dataList.add(new HashMap<>() {
                {
                    put("nonce", String.valueOf(System.currentTimeMillis()));
                }
            });
            header = generateKrakenPrivate(path, header, dataList);

        }

        String query = queryBuilder(dataList);

        try {

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL((baseUrl + path)).openConnection();
            httpsURLConnection.setRequestMethod(method.toString());

            for(int i = 0; i < header.getName().size(); i++) {

                httpsURLConnection.addRequestProperty(header.getName().get(i), header.getValue().get(i).toString());

            }

            if(!query.isEmpty()) {

                httpsURLConnection.setDoOutput(true);

            }

            logger.info("Request to : " + baseUrl + path + " with parameter : " + query);

            try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream())) {

                outputStreamWriter.write(query);

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while(line != null) {

                stringBuilder.append(line);

                line = bufferedReader.readLine();

            }

            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(stringBuilder.toString(), new TypeReference<>() {
            });

            logger.info("Response from : " + baseUrl + path + " with parameter : " + stringBuilder.toString());

            httpsURLConnection.disconnect();

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


}
