package com.jfb.lecture5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;
import com.jfb.lecture5.model.Validator;

import java.io.*;
import java.util.ArrayList;


public class Main {
  public static void main(String[] args) throws IOException, IllegalAccessException {

    File file = new File("src/main/resources/ticketData.txt");
    ArrayList<BusTicket> tickets = readTheFile(file);
    Validator validator = new Validator(tickets);
    System.out.println("Valid tickets: " + validator.getValidTicketCounter() + '\n' +
            "Invalid ticket by type: " + validator.getInvalidByTypeTicketCounter() + '\n' +
            "Invalid ticket by start date: " + validator.getInvalidByStartDateTicketCounter() + "\n" +
            "Invalid ticket by price: " + validator.getInvalidByPriceTicketCounter());

    if (validator.getInvalidByTypeTicketCounter() > validator.getInvalidByStartDateTicketCounter() &
            validator.getInvalidByTypeTicketCounter() > validator.getInvalidByPriceTicketCounter()) {
      System.out.println("The most common violation is by Type");
    } else if (validator.getInvalidByStartDateTicketCounter() > validator.getInvalidByTypeTicketCounter() &
            validator.getInvalidByStartDateTicketCounter() > validator.getInvalidByPriceTicketCounter()) {
      System.out.println("The most common violation is by Start Date");
    } else if (validator.getInvalidByPriceTicketCounter() > validator.getInvalidByTypeTicketCounter() &
            validator.getInvalidByPriceTicketCounter() > validator.getInvalidByStartDateTicketCounter()) {
      System.out.println("The most common violation is by Price");
    }
  }

    private static ArrayList<BusTicket> readTheFile (File file) throws IOException, IllegalAccessException {
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayList<BusTicket> tickets = new ArrayList<>();
      try (
              FileReader reader = new FileReader(file);
              BufferedReader bufferedReader = new BufferedReader(reader);
      ) {
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
          BusTicket ticket = objectMapper.readValue(currentLine, BusTicket.class);

          tickets.add(ticket);
        }
      }
      return tickets;
    }

}