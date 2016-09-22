package com.mac.nytimes.interfaces;

import com.mac.nytimes.dto.TopStoriesResponse;

public interface RestAPIListener {
    void onSuccess(Object responseObj);

    void onFailure(String localizedMessage);
}
