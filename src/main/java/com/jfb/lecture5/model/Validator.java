package com.jfb.lecture5.model;


import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

public class Validator {
    private int validTicketCounter = 0;
    private int invalidByPriceTicketCounter = 0;
    private int invalidByTypeTicketCounter = 0;
    private int invalidByStartDateTicketCounter = 0;


    public Validator(ArrayList<BusTicket> tickets) {
        for (BusTicket ticket : tickets) {
            if (isTicketValidByPrice(ticket) &
                    isTicketValidateByType(ticket) &
                    isTicketValidateByStartDate(ticket)) {
                validTicketCounter++;
            }

        }
    }

    public int getValidTicketCounter() {
        return validTicketCounter;
    }

    public int getInvalidByPriceTicketCounter() {
        return invalidByPriceTicketCounter;
    }

    public int getInvalidByTypeTicketCounter() {
        return invalidByTypeTicketCounter;
    }

    public int getInvalidByStartDateTicketCounter() {
        return invalidByStartDateTicketCounter;
    }

    private boolean isTicketValidByPrice(BusTicket ticket) {
        boolean isValid = true;
        if (ticket.getPrice() == null ||
                Integer.parseInt(ticket.getPrice()) == 0 ||
                Integer.parseInt(ticket.getPrice()) % 2 == 1) {
            invalidByPriceTicketCounter++;
            isValid = false;
        }
        return isValid;

    }

    private boolean isTicketValidateByType(BusTicket ticket) {
        boolean isValid = true;
        if (ticket.getTicketType() == null) {
            invalidByTypeTicketCounter++;
            isValid = false;
        } else {
            switch (ticket.getTicketType()) {
                case "DAY", "WEEK", "YEAR":
                    if (ticket.getStartDate() == null) {
                        invalidByTypeTicketCounter++;
                        isValid = false;
                        break;
                    }
                case "MONTH":
                    break;
                default:
                    invalidByTypeTicketCounter++;
                    isValid = false;
            }
        }
        return isValid;
    }

    private boolean isTicketValidateByStartDate(BusTicket ticket) {
        boolean isValid = true;
        if (ticket.getStartDate() == null ||
                ticket.getStartDate().isEmpty() ||
                LocalDate.now().isBefore(LocalDate.parse(ticket.getStartDate()))) {
            invalidByStartDateTicketCounter++;
            isValid = false;
        }
        return isValid;
    }

}