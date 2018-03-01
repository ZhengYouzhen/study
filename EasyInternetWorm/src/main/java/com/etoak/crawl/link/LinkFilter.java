package com.etoak.crawl.link;

public interface LinkFilter {
    boolean accept(String url);
}
