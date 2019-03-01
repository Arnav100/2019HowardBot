package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;

public class FishDetector extends DogeCVDetector {

    public double x;
    public double y;

    @Override
    public Mat process(Mat rgba) {
        x = 0;
        y = 0;

        Mat bitmask = new Mat();

        // Core.inRange(rgba, new Scalar(127, 50, 0), new Scalar(255, 127, 50), bitmask);
        Core.inRange(rgba, new Scalar(50, 50, 50), new Scalar(255, 255, 255), bitmask);

        ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        Imgproc.findContours(bitmask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        if(contours.isEmpty()) {
            x = Double.NaN;
            y = Double.NaN;
            return rgba;
        }

        MatOfPoint largest = contours.get(0);

        for(MatOfPoint contour : contours) {
            if(Imgproc.contourArea(contour) > Imgproc.contourArea(largest)) {
                largest = contour;
            }
        }

        Moments m = Imgproc.moments(largest);

        // Center of mass of contour
        x = m.m10 / m.m00 / rgba.width();
        y = m.m01 / m.m00 / rgba.height();

        return rgba;
    }
    @Override
    public void useDefaults() {

    }
}