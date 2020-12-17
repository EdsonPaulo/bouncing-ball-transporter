/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author EdsonPaulo
 */
public class Ball {

    // BALL PROPERTIES
    private BufferedImage ballImg;
    private final int BALL_SIZE = 50;
    private int ballPositionX = 100;
    private int ballPositiony = 100;
    private double ballVerticalSpeed = 40;
    private double ballHorizontalSpeed = 5;

    // SPACE PROPERTIES RELATED TO BALL
    private double gravity = 1;
    private double ballPressure = 0.1;
    private double arResistance = 0.00001;
    private int horizontalVelocity = 10;

    public Ball() {
        try {
            ballImg = ImageIO.read(new File("assets/images/Ball.png"));
        } catch (IOException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawBall(Graphics2D g2D) {

        g2D.drawImage(
                ballImg, ballPositionX, ballPositiony,
                ballPositionX + BALL_SIZE, ballPositiony + BALL_SIZE,
                0, 0, ballImg.getWidth(), ballImg.getHeight(),
                null);

        applyGravity();
        applyHorizontalSpeed();
        keepOnScreenBounds();
    }

    /// HANDLING BALL ON VERTICAL DIRECTION (Y)
    private void applyGravity() {
        ballVerticalSpeed += gravity;
        ballPositiony += ballVerticalSpeed;
        ballVerticalSpeed -= (ballVerticalSpeed * arResistance);
    }

    public void makeBounceBottom(int surface) {
        ballPositiony = surface - (BALL_SIZE / 2);
        ballVerticalSpeed *= -1;
        ballVerticalSpeed -= (ballVerticalSpeed * ballPressure);
    }

    public void makeBounceTop(int surface) {
        ballPositiony = surface + (BALL_SIZE / 2);
        ballVerticalSpeed *= -1;
        ballVerticalSpeed -= (ballVerticalSpeed * ballPressure);
    }
    
    /// HANDLING BALL ON HORIZONTAL DIRECTION (X)
    private void applyHorizontalSpeed() {
        ballPositionX += ballHorizontalSpeed;
        ballHorizontalSpeed -= (ballHorizontalSpeed * arResistance);
    }

    private void makeBounceLeft(int surface) {
        ballPositionX = surface + (BALL_SIZE / 2);
        ballHorizontalSpeed *= -1;
        ballHorizontalSpeed -= (ballHorizontalSpeed * ballPressure);
    }

    private void makeBounceRight(int surface) {
        ballPositionX = surface - (BALL_SIZE / 2);
        ballHorizontalSpeed *= -1;
        ballHorizontalSpeed -= (ballHorizontalSpeed * ballPressure);
    }

    // keep ball in the screen
    private void keepOnScreenBounds() {
        if (ballPositiony + (BALL_SIZE / 2) > 845) {
            makeBounceBottom(845);
        }
        if (ballPositiony - (BALL_SIZE / 2) < 0) {
            makeBounceTop(0);
        }
        if (ballPositionX - (BALL_SIZE / 2) < 0) {
            makeBounceLeft(0);
        }
        if (ballPositionX + (BALL_SIZE / 2) > 1366) {
            makeBounceRight(1366);
        }
    }

    public int getBallPositionX() {
        return ballPositionX;
    }

    public void setBallPositionX(int ballPositionX) {
        this.ballPositionX = ballPositionX;
    }

    public int getBallPositiony() {
        return ballPositiony;
    }

    public void setBallPositiony(int ballPositiony) {
        this.ballPositiony = ballPositiony;
    }

    public double getBallVerticalSpeed() {
        return ballVerticalSpeed;
    }

    public void setBallVerticalSpeed(double ballVerticalSpeed) {
        this.ballVerticalSpeed = ballVerticalSpeed;
    }

    public double getBallHorizontalSpeed() {
        return ballHorizontalSpeed;
    }

    public void setBallHorizontalSpeed(double ballHorizontalSpeed) {
        this.ballHorizontalSpeed = ballHorizontalSpeed;
    }

    public int getHorizontalVelocity() {
        return horizontalVelocity;
    }

    public void setHorizontalVelocity(int horizontalVelocity) {
        this.horizontalVelocity = horizontalVelocity;
    }

    public int getBALL_SIZE() {
        return BALL_SIZE;
    }
}
