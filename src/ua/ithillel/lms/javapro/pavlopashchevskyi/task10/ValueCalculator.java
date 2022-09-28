package ua.ithillel.lms.javapro.pavlopashchevskyi.task10;

import java.util.Arrays;

public class ValueCalculator {

  private static final int MIN_VALUES_ARRAY_SIZE = 1000000;
  private final float[] values;
  private final int size;
  private final int halfSize;

  public ValueCalculator(int size, float value) {
    this.size = Math.max(size, MIN_VALUES_ARRAY_SIZE);
    this.halfSize = this.size / 2;
    this.values = new float[size];
    Arrays.fill(values, value); //fill array by the same values
  }

  public void doCalc() throws InterruptedException {
    long startTime = System.currentTimeMillis(); //fix method start time
    float[] theFirstHalf = new float[halfSize];
    float[] theSecondHalf = new float[halfSize];
    System.arraycopy(values, 0, theFirstHalf, 0, halfSize); // copy the 1st half
    System.arraycopy(values, halfSize, theSecondHalf, 0, halfSize); // copy the 2nd half

    Thread theFirstHalfCalcThread = new Thread(() -> { // recalculate the first half in one thread
      recalculateArrayValues(theFirstHalf);
    });
    Thread theSecondHalfCalcThread = new Thread(() -> { // recalculate the second half in 2nd thread
      recalculateArrayValues(theSecondHalf);
    });
    theFirstHalfCalcThread.start();
    theSecondHalfCalcThread.start();

    theFirstHalfCalcThread.join();
    System.arraycopy(theFirstHalf, 0, values, 0, halfSize); // concatenate arrays
    theSecondHalfCalcThread.join();
    System.arraycopy(theSecondHalf, 0, values, halfSize, halfSize);

    long execTime = System.currentTimeMillis() - startTime; // calculate execution time
    System.out.println("ValueCalculator.doCalc() method had been being executed for " + execTime
        + " ms.");
  }

  public String outputValues() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      sb.append(values[i]);
      sb.append(" ");
    }
    sb.append("\n");

    return sb.toString();
  }

  private void recalculateArrayValues(float[] arr) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.) * Math.cos(0.2f + i / 5.) *
          Math.cos(0.4f + i / 2.));
    }
  }
}
