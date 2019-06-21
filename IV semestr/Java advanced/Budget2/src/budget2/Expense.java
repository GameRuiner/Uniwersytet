/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget2;

import java.time.LocalDate;

/**
 *
 * @author MarkG
 */
public class Expense {
    private String name;
    private String date;
    private String type;
    private double amount;
    private String currency;
    
    public Expense(String name, String date, String type, double amount, String currency) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return name + " " + date + " " + type + " " + amount +" "+currency;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
