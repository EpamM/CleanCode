package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    private MortgageInstallmentCalculator() {
        throw new IllegalStateException("MortgageInstallmentCalculator class");
    }

    /**
     * get monthly payment amount
     *
     * @param principalAmount principal amount
     * @param termofMortgage term of mortgage in years
     * @param rateOfInterest rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(int principalAmount, int termofMortgage, double rateOfInterest) {

        if (principalAmount < 0 || termofMortgage <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        rateOfInterest /= 100.0;

        double tim = (double) termofMortgage * 12;

        if(rateOfInterest==0)
            return  principalAmount/tim;

        double monthRateOfInterest = rateOfInterest / 12.0;

        // The Math.pow() method is used calculate values raised to a power
        return (principalAmount * monthRateOfInterest) / (1 - Math.pow(1 + monthRateOfInterest, -tim));
    }
}
