package edu.kis.powp.jobs2d.drivers.transformation;

import edu.kis.powp.jobs2d.Job2dDriver;

public class TransformerDriverDecorator implements Job2dDriver {
    private final Job2dDriver driver;
    private final TransformStrategy strategy;

    public TransformerDriverDecorator(Job2dDriver driver, TransformStrategy strategy) {
        this.driver = driver;
        this.strategy = strategy;
    }

    @Override
    public void setPosition(int x, int y) {
        TransformCords cords = strategy.transform(new TransformCords(x, y));
        this.driver.setPosition(cords.x, cords.y);
    }

    @Override
    public void operateTo(int x, int y) {
        TransformCords cords = strategy.transform(new TransformCords(x, y));
        this.driver.operateTo(cords.x, cords.y);
    }

    @Override
    public String toString() {
        return "Transformer Driver";
    }
}