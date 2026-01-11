package edu.kis.powp.jobs2d.drivers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.visitor.VisitableJob2dDriver;
import edu.kis.powp.jobs2d.visitor.DriverVisitor;

public class DriverComposite implements VisitableJob2dDriver {
    private List<Job2dDriver> drivers;

    public DriverComposite(List<Job2dDriver> drivers) {
        this.drivers = drivers;
    }

    public void add(Job2dDriver driver) {
        drivers.add(driver);
    }

    public void remove(Job2dDriver driver) {
        drivers.remove(driver);
    }

    public Iterator<Job2dDriver> iterator() {
        return drivers.iterator();
    }

    @Override
    public void setPosition(int x, int y) {
        drivers.stream().forEach(driver -> driver.setPosition(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        drivers.stream().forEach(driver -> driver.operateTo(x, y));
    }

    @Override
    public void accept(DriverVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.join(" + ", drivers.stream().map(driver -> driver.toString()).collect(Collectors.toList()));
    }
}
