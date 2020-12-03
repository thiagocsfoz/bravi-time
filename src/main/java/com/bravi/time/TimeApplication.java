package com.bravi.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class TimeApplication implements CommandLineRunner
{
    @Autowired
    private Environment environment;

    private static Logger LOG = LoggerFactory
            .getLogger(TimeApplication.class);

    public static void main(String[] args)
    {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(TimeApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args)
    {
        if(environment.getActiveProfiles().length == 0)
        {
            while(true)
            {
                System.out.println("Enter time (hh:mm):");
                Scanner scanner = new Scanner(System.in);
                ConvertTime convertTime = new ConvertTime();
                // get their input as a String
                String time = scanner.next();

                if(time.equals("exit"))
                    break;

                try {
                    System.out.println(convertTime.convert(time));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }

}

