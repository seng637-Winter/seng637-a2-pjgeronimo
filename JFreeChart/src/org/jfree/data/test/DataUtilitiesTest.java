package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

public class DataUtilitiesTest {

    //calculateColumnTotal***********************************************************

    @Test
    public void calculateColumnTotalForTwoValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values).getValue(1, 0);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test
    public void calculateColumnTotalForTwoNegValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-7.5));
            oneOf(values).getValue(1, 0);
            will(returnValue(-2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(-10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test
    public void calculateColumnTotalForTwoOppositeSignedValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-7.5));
            oneOf(values).getValue(1, 0);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(-5, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test
    public void calculateColumnTotalForTwoZeros() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(0));
            oneOf(values).getValue(1, 0);
            will(returnValue(0));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(0, result, .000000001d);

        // tear-down: NONE in this test method
    }

    //calculateRowTotal***********************************************************

    @Test
    public void calculateRowForTwoValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getColumnCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values).getValue(0, 1);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 0);
        
        // verify
        assertEquals(10, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for a row with two negative values
    @Test
    public void calculateRowForTwoNegValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-7.5));
            oneOf(values).getValue(0, 1);
            will(returnValue(-2.5));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 0);

        // verify
        assertEquals(-10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for a row with two opposite signed values
    @Test
    public void calculateRowForTwoOppositeSignedValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-7.5));
            oneOf(values).getValue(0, 1);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 0);

        // verify
        assertEquals(-5, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    //CreateNumberArray***********************************************************
    
    // Test for a normal case with multiple values
    @Test
    public void testCreateNumberArrayWithNonEmptyArray() {
        // setup
        double[] data = {1.0, 2.0, 3.0};
        Number[] expected = {1.0, 2.0, 3.0};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for a boundary case with a single element 
    @Test
    public void testCreateNumberArrayWithSingleElement() {
        // setup
        double[] data = {1.0};
        Number[] expected = {1.0};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    
    // Test for handling decimal values
    @Test
    public void testCreateNumberArrayWithDecimalValues() {
        // setup
        double[] data = {1.5, 2.75, 3.125};
        Number[] expected = {1.5, 2.75, 3.125};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for an empty array case 
    @Test
    public void testCreateNumberArrayWithEmptyArray() {
        // setup
        double[] data = new double[0];
        Number[] expected = new Number[0];

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }

    // Test for handling null input 
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArrayWithNullArray() {
        // setup
        // No setup needed for this test

        // exercise and verify
        DataUtilities.createNumberArray(null);

        // tear-down: NONE in this test method
    }
    
    //CreateNumberArray2D***********************************************************

    // Test for a normal 2D array with multiple values in each inner array
    @Test
    public void testCreateNumberArray2DWithNonEmptyArray() {
        // setup
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for a boundary case with a single element in a 2D array
    @Test
    public void testCreateNumberArray2DWithSingleElement() {
        // setup
        double[][] data = {{1.0}};
        Number[][] expected = {{1.0}};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for handling uneven arrays 
    @Test
    public void testCreateNumberArray2DWithUnevenArray() {
        // setup
        double[][] data = {{1.0, 2.0}, {3.0}};
        Number[][] expected = {{1.0, 2.0}, {3.0}};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }

    // Test for 2D arrays with empty inner arrays 
    @Test
    public void testCreateNumberArray2DWithEmptyInnerArrays() {
        // setup
        double[][] data = {new double[0], new double[0]};
        Number[][] expected = {new Number[0], new Number[0]};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for entirely empty 2D array 
    @Test
    public void testCreateNumberArray2DWithEmptyArray() {
        // setup
        double[][] data = new double[0][0];
        Number[][] expected = new Number[0][0];

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }

    // Test for handling null input for 2D arrays
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithNullArray() {
        DataUtilities.createNumberArray2D(null);
    }

    //getCumulativePercentages***********************************************************

    // Test for a normal case with single value
    @Test
    public void testGetCumulativePercentagesWithSingleValue() {
        // setup
        Mockery context = new Mockery();
        final KeyedValues keyedValues = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            // Setting expectations for single value
            allowing(keyedValues).getItemCount(); will(returnValue(1));
            allowing(keyedValues).getKey(0); will(returnValue(0));
            allowing(keyedValues).getValue(0); will(returnValue(5.0));
        }});

        double[] expected = {1.0};

        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for a normal case with multiple values
    @Test
    public void testGetCumulativePercentagesWithMultipleValues() {
        // setup
        Mockery context = new Mockery();
        final KeyedValues keyedValues = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            // Setting expectations for values
            allowing(keyedValues).getItemCount(); will(returnValue(3));
            allowing(keyedValues).getKey(0); will(returnValue(0));
            allowing(keyedValues).getValue(0); will(returnValue(1.0));
            allowing(keyedValues).getKey(1); will(returnValue(1));
            allowing(keyedValues).getValue(1); will(returnValue(2.0));
            allowing(keyedValues).getKey(2); will(returnValue(2));
            allowing(keyedValues).getValue(2); will(returnValue(3.0));
        }});

        double[] expected = {(double) 1.0/6.0, (double) 3.0/6.0, 1.0};

        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for handling decimal values
    @Test
    public void testGetCumulativePercentagesWithDecimalValues() {
        // setup
        Mockery context = new Mockery();
        final KeyedValues keyedValues = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            // Setting expectations for decimal values
            allowing(keyedValues).getItemCount(); will(returnValue(3));
            allowing(keyedValues).getKey(0); will(returnValue(0));
            allowing(keyedValues).getValue(0); will(returnValue(1.5));
            allowing(keyedValues).getKey(1); will(returnValue(1));
            allowing(keyedValues).getValue(1); will(returnValue(2.75));
            allowing(keyedValues).getKey(2); will(returnValue(2));
            allowing(keyedValues).getValue(2); will(returnValue(3.125));
        }});

        double[] expected = {(double) 1.5/(1.5+2.75+3.125), (double) (1.5+2.75)/(1.5+2.75+3.125), 1.0};

        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for handling zero values
    @Test
    public void testGetCumulativePercentagesWithZeroValues() {
        // setup
        Mockery context = new Mockery();
        final KeyedValues keyedValues = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            // Setting expectations for zero values
            allowing(keyedValues).getItemCount(); will(returnValue(3));
            allowing(keyedValues).getKey(0); will(returnValue(0));
            allowing(keyedValues).getValue(0); will(returnValue(0.0));
            allowing(keyedValues).getKey(1); will(returnValue(1));
            allowing(keyedValues).getValue(1); will(returnValue(0.0));
            allowing(keyedValues).getKey(2); will(returnValue(2));
            allowing(keyedValues).getValue(2); will(returnValue(0.0));
        }});

        double[] expected = {0.0/0.0, 0.0/0.0, 0.0/0.0};

        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }

}

