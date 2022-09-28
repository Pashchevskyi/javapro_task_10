package ua.ithillel.lms;

import ua.ithillel.lms.javapro.pavlopashchevskyi.task10.ValueCalculator;

public class Main {

  public static void main(String[] args) {
    ValueCalculator valueCalculator = new ValueCalculator(1000000, 2.0f);
    System.out.println("Before");
    System.out.println(valueCalculator.outputValues());
    try {
      valueCalculator.doCalc();
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("After");
    System.out.println(valueCalculator.outputValues());
  }
}
