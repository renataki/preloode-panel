package com.preloode.panel.webservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;


@WebServlet("/captcha")
public class CaptchaWebServlet extends HttpServlet {


    private static final Logger logger = LoggerFactory.getLogger(CaptchaWebServlet.class);

    private static String file = "png";

    private static int height = 40;

    private static int length = 5;

    private static int width = 120;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Progma", "no-cache");
        response.setDateHeader("Max-Age", 0);

        String text = initializeText(length);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setFont(new Font("Serif", Font.PLAIN, 26));
        graphics2D.setColor(Color.blue);

        int start = 10;
        byte[] textByte = text.getBytes();
        Random random = new Random();

        for(int i = 0; i < textByte.length; i++) {

            graphics2D.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            graphics2D.drawString(new String(new byte[]{textByte[i]}), start + (i * 20), (int) (Math.random() * 20 + 20));

        }

        graphics2D.setColor(Color.white);

        for(int i = 0; i < 8; i++) {

            graphics2D.drawOval((int) (Math.random() * 160), (int) (Math.random() * 10), 30, 30);

        }

        try {

            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, file, outputStream);
            outputStream.close();

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        session.setAttribute("captcha", text);

        logger.info("Captcha initialized");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        doPost(request, response);

    }


    private String initializeText(Integer length) {

        String result = "";
        String character = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        while(stringBuffer.length() < length) {

            int index = (int) (random.nextFloat() * character.length());
            stringBuffer.append(character.substring(index, index + 1));

        }

        result = stringBuffer.toString();

        logger.info("Captcha text initialized");

        return result;

    }


}
