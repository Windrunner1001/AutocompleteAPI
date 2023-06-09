package com.CaseStudy.AutocompleteAPI;


import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class AutocompleteController {

    @GetMapping({"/auto-complete/", "/auto-complete/{userInput}"})
    public Response compareData(@PathVariable Optional<String> userInput) throws IOException {

          //start timer
        long startTime = System.currentTimeMillis();

        //list holding all matches
        List<String[]> result = new ArrayList<>();

        // Read CSV file and get the index of the compare-column "NAME"
        List<String[]> csvData = CSVHandler.readCsvFile();


        //only if there is a user input, get the matching values, else throw error
        if (userInput.isPresent()){
            String input = userInput.get();

            //if there is no user input, throw error
            if (input.length() < 3){throw new UserInputTooShort();}
            //if there are any digits, throw error
            if (Helper.containsDigits(input)){throw new UserInputNotAlphabetic();}

            // Compare user input to compareColumn (currently the column "NAME"). In case of match, add to the result.
            for (String[] row : csvData) {
                if (Helper.containsIgnoreCase(row[(CSVHandler.getIndexColumn())], input)) {
                    result.add(row);
                }
            }
        } else{
            throw new NoUserInput();
        }

        //if there are no matches, throw error
        if (result.isEmpty()) {
            throw new NoMatches();
        } else {
            //instantiate response-class to build custom json response
            Response response = new Response(result);

            //stop timer and set time in response
            long duration = System.currentTimeMillis() - startTime;
            response.setTime_taken(duration);
            return response;
        }
    }
}

