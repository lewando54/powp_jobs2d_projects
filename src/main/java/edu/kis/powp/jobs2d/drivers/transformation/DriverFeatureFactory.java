package edu.kis.powp.jobs2d.drivers.transformation;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class DriverFeatureFactory {

    public static Job2dDriver createRotateDriver(Job2dDriver baseDriver, double angle) {
        return new TransformerDriverDecorator(baseDriver, new RotateStrategy(angle));
    }

    public static Job2dDriver createScaleDriver(Job2dDriver baseDriver, double scale) {
        return new TransformerDriverDecorator(baseDriver, new ScaleStrategy(scale));
    }

    public static Job2dDriver createFlipDriver(Job2dDriver baseDriver, boolean flipX, boolean flipY) {
        return new TransformerDriverDecorator(baseDriver, new FlipStrategy(flipX, flipY));
    }
}