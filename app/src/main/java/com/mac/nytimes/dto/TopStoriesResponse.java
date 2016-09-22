package com.mac.nytimes.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mac.nytimes.models.Result;

public class TopStoriesResponse {
    private String status;
    private String copyright;
    private String section;
    private String lastUpdated;
    private Integer numResults;
    private List<Result> results = new ArrayList<Result>();

    /**
     *
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     *
     * @param copyright
     *     The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     *
     * @return
     *     The section
     */
    public String getSection() {
        return section;
    }

    /**
     *
     * @param section
     *     The section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     *
     * @return
     *     The lastUpdated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     *     The last_updated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return
     *     The numResults
     */
    public Integer getNumResults() {
        return numResults;
    }

    /**
     *
     * @param numResults
     *     The num_results
     */
    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    /**
     *
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
