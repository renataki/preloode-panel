package com.preloode.panel.component;

import com.preloode.panel.model.global.UrlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
public class UrlComponent {


    private static final Logger logger = LoggerFactory.getLogger(UrlComponent.class);


    public Map<String, Object> initializeBreadcrumb(Map<String, String> pathMap) {

        Map<String, Object> result = new HashMap<>();

        String url = "";

        for(Map.Entry<String, String> map : pathMap.entrySet()) {

            url = url + "/" + map.getValue();
            Map<String, String> breadcrumb = new HashMap<>();
            breadcrumb.put("text", map.getValue());
            breadcrumb.put("url", url.replaceFirst("/", ""));
            result.put(map.getKey(), breadcrumb);

        }

        logger.info("Breadcrumb initialized");

        return result;

    }


    public UrlResponse initializeUrl(Map<String, String> pathMap) {

        UrlResponse result = new UrlResponse() {
            {
                setPath("");
                setResponse("Failed to initialize url");
                setResult(false);
                setUrl("");
            }
        };

        for(Map.Entry<String, String> map : pathMap.entrySet()) {

            if(map.getKey().equals("url")) {

                result.setUrl(map.getValue());

            } else {

                result.setPath(result.getPath() + "/" + map.getValue());

            }

        }

        result.setPath(result.getPath().replaceFirst("/", ""));

        result.setResponse("Url initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public UrlResponse initializeMultipleUrl(ArrayList<String> path, ArrayList<String> url) {

        UrlResponse result = new UrlResponse() {
            {
                setPathList(new ArrayList<>());
                setResponse("Failed to initialize multiple url");
                setResult(false);
            }
        };

        for(int i = 0; i < path.size(); i++) {

            result.getPathList().add(path.get(i) + "/" + url.get(i));

            if(result.getPathList().get(i).startsWith("/")) {

                result.getPathList().set(i, result.getPathList().get(i).replaceFirst("/", ""));

            }

        }

        result.setResponse("Multiple url initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


}
