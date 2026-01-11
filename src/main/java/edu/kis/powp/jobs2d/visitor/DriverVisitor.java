package edu.kis.powp.jobs2d.visitor;


import edu.kis.powp.jobs2d.drivers.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.AnimatedDriverDecorator;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;

/**
 * Interface for the Visitor pattern to traverse and process drivers.
 */
public interface DriverVisitor {

    /**
     * Visits a AnimatedDriverDecorator.
     * @param AnimatedDriverDecorator driver to visit.
     */
    void visit(AnimatedDriverDecorator animatedDriverDecorator);

    /**
     * Visits an DriverComposite.
     *  @param DriverComposite driver to visit.
     */
    void visit(DriverComposite driverComposite);

    /**
     * Visits a LoggerDriver.
     * @param LoggerDriver the driver to visit.
     */
    void visit(LoggerDriver loggerDriver);

    /**
     * Visits a LineDriverAdapter.
     * @param LineDriverAdapter the adapter to visit.
     */
    void visit(LineDriverAdapter lineDriverAdapter);
}