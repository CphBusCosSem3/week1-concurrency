/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author jens
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        final Function<String, List<String>> myFunction = (String airline) -> {
            try {
                /// Asynkront!
                Thread.sleep(1000);
                return Arrays.asList(airline);
            } catch (Exception e) {
                return Collections.emptyList();
            }
        };
        
        List<String> endpoints = Arrays.asList("myAirport", "somethign");
        
        // First take the list and make it into a stream
        List<String> data = endpoints.stream()
                .map(myFunction) // Then apply 'myFunction'
                                 // Now you have a list of lists
                .parallel()      // Do this in parallel!   
                .flatMap(list -> list.stream()) // Turn the lists into streams
                .collect(Collectors.toList());  // And collect everything into 
                                                // one list!
                                               
        System.out.println(data);
    }

}

