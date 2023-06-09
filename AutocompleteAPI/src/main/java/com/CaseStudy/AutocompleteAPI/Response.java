package com.CaseStudy.AutocompleteAPI;

import java.util.ArrayList;
import java.util.List;


/**
 * Class Response provides a structure for the JSON response.
 **/

public class Response {

    private List<String> station_list;
    private String time_taken;
    private String number_of_stations_found;


    public Response(List<String[]> result) {
        List<String> station_list = new ArrayList<>();
        for (String[] match : result) {
            station_list.add((match[0] + " - " + match[1] + " - " + match[2]).toString());
        }
        setStation_list(station_list);
        setNumber_of_stations_found(String.valueOf(result.size()));
    }


    public List<String> getStation_list() {
        return station_list;
    }

    public void setStation_list(List<String> station_list) {
        this.station_list = station_list;
    }

    public String getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(float time_taken) {
        this.time_taken = time_taken + " ms";
    }

    public String getNumber_of_stations_found() {
        return number_of_stations_found;
    }

    public void setNumber_of_stations_found(String number_of_stations_found) {
        this.number_of_stations_found = number_of_stations_found;
    }
}
