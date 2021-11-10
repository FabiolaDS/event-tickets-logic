package com.eventtickets.logictier.network;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public abstract class RestRepository {
    @NonNull private RestTemplate restTemplate;
    @NonNull private String dataUrl;
    @NonNull private String prefix;

    protected RestTemplate rest() { return restTemplate; }

    protected String url() {
        return String.format("%s/%s", dataUrl, prefix);
    }

    protected String url(long id) {
        return String.format("%s/%s/%d", dataUrl, prefix, id);
    }

    protected String url(String suffix) {
        return String.format("%s/%s/%s", dataUrl, prefix, suffix);
    }

    protected String url(String suffix, long id) {
        return String.format("%s/%s/%s/%d", dataUrl, prefix, suffix, id);
    }
}
