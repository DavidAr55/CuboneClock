import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class CuboneClock extends JFrame {

    private BufferedImage buffer;
    private Graphics2D graPixel;
    private TxtCords txtCords;

    private int centerX;
    private int centerY;
    private int clockRadius;
    private int currentHour;
    private int currentMinute;
    private int currentSecond;

    public CuboneClock() {

        centerX = 53;
        centerY = 364;
        clockRadius = 20;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 560);
        setLocationRelativeTo(null);

        // Inicializa el buffer con las mismas dimensiones que la ventana
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        graPixel = buffer.createGraphics();
        txtCords = new TxtCords();

        // Establece el color de fondo del buffer
        Graphics2D g2d = (Graphics2D) graPixel;
        g2d.setBackground(Color.WHITE); // Cambia Color.WHITE al color que desees
        g2d.clearRect(0, 0, getWidth(), getHeight());
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    @Override
    public void paint(Graphics g) {

        drawPockemon(graPixel);
        drawClock(graPixel, centerX, centerY, clockRadius, currentHour, currentMinute, currentSecond);

        g.drawImage(buffer, 0, 0, this);
    }

    public void drawPockemon(Graphics2D g2) {

        String fileHead = "./cords/Head.txt";

        int[] xPointsHead = txtCords.convertTxtToXPoints(fileHead);
        int[] yPointsHead = txtCords.convertTxtToYPoints(fileHead);
        int vertexHead = txtCords.countVertices(fileHead);

        
        Polygon Head = new Polygon(xPointsHead, yPointsHead, vertexHead);
        g2.setColor(Color.decode("#E2DFDA"));
        g2.fillPolygon(Head);

        g2.setColor(Color.decode("#54554F")); 
        g2.drawPolygon(Head); 


        String fileBody = "./cords/Body.txt";

        int[] xPointsBody = txtCords.convertTxtToXPoints(fileBody);
        int[] yPointsBody = txtCords.convertTxtToYPoints(fileBody);
        int vertexBody = txtCords.countVertices(fileBody);

        
        Polygon Body = new Polygon(xPointsBody, yPointsBody, vertexBody);
        g2.setColor(Color.decode("#CA944E"));
        g2.fillPolygon(Body);

        g2.setColor(Color.decode("#703C03")); 
        g2.drawPolygon(Body); 


        String fileBelly = "./cords/Belly.txt";

        int[] xPointsBelly = txtCords.convertTxtToXPoints(fileBelly);
        int[] yPointsBelly = txtCords.convertTxtToYPoints(fileBelly);
        int vertexBelly = txtCords.countVertices(fileBelly);

        
        Polygon Belly = new Polygon(xPointsBelly, yPointsBelly, vertexBelly);
        g2.setColor(Color.decode("#EFE1C7"));
        g2.fillPolygon(Belly);


        int[] arrayLineX = {144, 130, 117, 101, 90, 83, 79, 77, 77, 80, 85, 94, 105, 119, 129};
        int[] arrayLineY = {493, 483, 474, 461, 443, 427, 410, 393, 384, 372, 355, 339, 323, 312, 309};
        drawLines(g2, arrayLineX, arrayLineY, Color.decode("#703C03"));


        int[] arrayLineX_2 = {314, 309, 305, 307, 310, 310, 307, 305, 300, 293, 291, 289, 289, 279, 265, 251};
        int[] arrayLineY_2 = {426, 427, 425, 421, 417, 417, 421, 425, 423, 417, 415, 411, 402, 392, 380, 369};
        drawLines(g2, arrayLineX_2, arrayLineY_2, Color.decode("#703C03"));



        String fileBone = "./cords/Bone.txt";

        int[] xPointsBone = txtCords.convertTxtToXPoints(fileBone);
        int[] yPointsBone = txtCords.convertTxtToYPoints(fileBone);
        int vertexBone = txtCords.countVertices(fileBone);

        Polygon Bone = new Polygon(xPointsBone, yPointsBone, vertexBone);
        g2.setColor(Color.decode("#E2DFDA"));
        g2.fillPolygon(Bone);

        g2.setColor(Color.decode("#54554F")); 
        g2.drawPolygon(Bone); 


        int[] arrayLineX_3 = {104, 105, 111, 120, 126, 135, 150, 160, 168, 175, 181, 188, 195, 199, 201, 200, 194, 184, 169, 155, 127, 116, 110, 104};
        int[] arrayLineY_3 = {193, 175, 157, 141, 132, 126, 126, 130, 135, 142, 150, 162, 175, 187, 196, 209, 221, 231, 238, 240, 240, 226, 213, 193};
        drawLines(g2, arrayLineX_3, arrayLineY_3, Color.decode("#54554F"));


        int[] arrayLineX_4 = {128, 122, 122, 131, 136, 143, 159, 170, 182, 193, 201, 190, 179, 166, 157, 147, 140, 135, 126, 125, 128};
        int[] arrayLineY_4 = {216, 203, 184, 159, 147, 144, 144, 150, 163, 177, 196, 179, 167, 156, 153, 153, 155, 163, 185, 202, 216};

        Polygon EyeShadow = new Polygon(arrayLineX_4, arrayLineY_4, arrayLineX_4.length - 1);
        g2.setColor(Color.BLACK);
        g2.fillPolygon(EyeShadow);


        int[] arrayLineX_7 = {201, 190, 179, 166, 157, 147, 140, 135, 126, 125, 128, 134, 142, 150, 169, 184, 194, 200, 201};
        int[] arrayLineY_7 = {196, 179, 167, 156, 153, 153, 155, 163, 185, 202, 216, 227, 233, 238, 238, 231, 221, 209, 196};

        Polygon BackgroundEye = new Polygon(arrayLineX_7, arrayLineY_7, arrayLineX_7.length - 1);
        g2.setColor(Color.decode("#CA944E"));
        g2.fillPolygon(BackgroundEye);


        int[] arrayLineX_5 = {128, 134, 142, 150, 169};
        int[] arrayLineY_5 = {216, 228, 233, 238, 238};
        drawLines(g2, arrayLineX_5, arrayLineY_5, Color.BLACK);


        int[] arrayLineX_6 = {198, 194, 189, 185, 180};
        int[] arrayLineY_6 = {213, 202, 191, 182, 172};
        drawLines(g2, arrayLineX_6, arrayLineY_6, Color.BLACK);


        int[] xPointsEye = {146, 191, 191, 183, 175, 168, 161, 155, 151, 147, 142, 140, 142};
        int[] yPointsEye = {214, 224, 214, 198, 184, 174, 168, 166, 168, 173, 180, 193, 211};

        Polygon Eye = new Polygon(xPointsEye, yPointsEye, xPointsEye.length - 1);
        g2.setColor(Color.WHITE);
        g2.fillPolygon(Eye);


        // Coordenadas del polígono
        int[] xPointsPupil = {162, 160, 159, 159, 161, 164, 168, 175, 183, 188, 187, 184, 172};
        int[] yPointsPupil = {218, 214, 206, 197, 187, 178, 174, 184, 198, 210, 217, 223, 223};

        // Crear un objeto GradientPaint para el degradado
        Color startColor = Color.GREEN;
        Color endColor = new Color(0, 25, 0); // Verde oscuro
        GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, yPointsPupil[yPointsPupil.length - 1], endColor);

        // Crear el polígono con el degradado
        Polygon Pupil = new Polygon(xPointsPupil, yPointsPupil, xPointsPupil.length - 1);

        g2.setPaint(gradient); // Establecer el degradado como el relleno
        g2.fillPolygon(Pupil);


        g2.setColor(Color.WHITE); // Color del borde
        g2.fillOval(171, 190, 9, 17);


        g2.setColor(Color.decode("#703C03")); 
        g2.drawPolygon(Eye);


        int[] xPointsBelt = {34, 40, 71, 64};
        int[] yPointsBelt = {350, 344, 382, 387};

        Polygon Belt = new Polygon(xPointsBelt, yPointsBelt, xPointsBelt.length);
        g2.setColor(Color.BLACK);
        g2.fillPolygon(Belt);


        // Bufer
        g2.drawImage(buffer, 0, 0, this);
    }

    public void drawLine(int width, int height) {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(width, height));
    }

    public void drawLines(Graphics2D g, int[] xPoints, int[] yPoints, Color c) {
        if (xPoints.length != yPoints.length) {
            throw new IllegalArgumentException("Arrays de coordenadas de diferente longitud.");
        }

        for (int i = 0; i < xPoints.length - 1; i++) {
            g.setColor(c);
            g.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
        }
    } 

    // Algoritmo de Bresenham para dibujar una línea
    public void drawLineBresenham(int x1, int y1, int x2, int y2, Color c) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x1, y1, c);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x1 = x1 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y1 = y1 + sy;
            }
        }
    }

    public void drawClock(Graphics2D g, int centerX, int centerY, int radius, int hour, int minute, int second) {
        // Dibujar el círculo del reloj
        g.setColor(Color.WHITE);
        g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    
        // Dibujar las manecillas del reloj
        int secondX = (int) (centerX + 0.8 * radius * Math.sin(Math.toRadians(6 * second)));
        int secondY = (int) (centerY - 0.8 * radius * Math.cos(Math.toRadians(6 * second)));
    
        int minuteX = (int) (centerX + 0.7 * radius * Math.sin(Math.toRadians(6 * (minute + hour * 60))));
        int minuteY = (int) (centerY - 0.7 * radius * Math.cos(Math.toRadians(6 * (minute + hour * 60))));
    
        int hourX = (int) (centerX + 0.6 * radius * Math.sin(Math.toRadians(30 * hour + 0.5 * minute)));
        int hourY = (int) (centerY - 0.6 * radius * Math.cos(Math.toRadians(30 * hour + 0.5 * minute)));
    
        drawLineBresenham(centerX, centerY, hourX, hourY, Color.BLACK);
        drawLineBresenham(centerX, centerY, minuteX, minuteY, Color.BLACK);
        drawLineBresenham(centerX, centerY, secondX, secondY, Color.RED);
    
        // Dibujar el centro del reloj
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 2, centerY - 2, 4, 4);
    
        // Dibujar números de las horas
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 8));
    
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(30 * i);
            int numX = (int) (centerX + 0.8 * radius * Math.sin(angle)) - 6;
            int numY = (int) (centerY - 0.8 * radius * Math.cos(angle)) + 6;
    
            g.drawString(Integer.toString(i), numX + 4, numY - 2);
        }
    }

    public void update() {

        // Crea un temporizador para actualizar el reloj cada segundo
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Obtén la hora actual en la zona horaria predeterminada
                Calendar now = Calendar.getInstance(TimeZone.getDefault());

                // Ajusta la hora actual restando una hora
                now.add(Calendar.HOUR_OF_DAY, -1);

                // Actualiza las variables de hora, minuto y segundo
                currentHour = now.get(Calendar.HOUR_OF_DAY);
                currentMinute = now.get(Calendar.MINUTE);
                currentSecond = now.get(Calendar.SECOND);

                // Vuelve a pintar el panel para actualizar el reloj
                repaint();
            }
        }, 0, 1000); // Actualizar cada segundo (1000 milisegundos)
    }
}
