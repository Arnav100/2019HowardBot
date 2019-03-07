package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
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

        Core.inRange(rgba, new Scalar(215, 0, 0, 0), new Scalar(255, 165, 135, 255), bitmask);

        ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        Imgproc.findContours(bitmask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        if(contours.isEmpty()) {
            Imgproc.line(rgba, new Point(0, rgba.height() / 2), new Point(rgba.width(), rgba.height() / 2), new Scalar(0, 255, 0));
            Imgproc.line(rgba, new Point(rgba.width() / 2, 0), new Point(rgba.width() / 2, rgba.height()), new Scalar(0, 255, 0));

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
        x = m.m10 / m.m00 / rgba.width() - 0.5;
        y = m.m01 / m.m00 / rgba.height() - 0.5;

        // Don't move too fast!
        x /= 2;
        y /= 2;

        Mat dst = new Mat();
        rgba.copyTo(dst, bitmask);
        Imgproc.line(dst, new Point(0, rgba.height() / 2), new Point(rgba.width(), rgba.height() / 2), new Scalar(0, 255, 0));
        Imgproc.line(dst, new Point(rgba.width() / 2, 0), new Point(rgba.width() / 2, rgba.height()), new Scalar(0, 255, 0));

        return dst;
    }
    @Override
    public void useDefaults() {

    }
}
